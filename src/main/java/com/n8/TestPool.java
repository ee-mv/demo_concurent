package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author micha
 */
@Slf4j(topic = "c.Test1")
public class TestPool {

    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(2, 1000, TimeUnit.SECONDS, 10,
                (queue, task) -> {
                    //queue.put(task);
                    queue.offer(task, 500, TimeUnit.MILLISECONDS);
                });

        for (int i = 0; i < 3; i++) {
            int j = i;
            threadPool.excute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("{}", j);
            });

        }
    }
}

interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}

@Slf4j(topic = "c.ThreadPool")
class ThreadPool {
    //  任务队列
    private BlockingQueue<Runnable> taskQueue;

    //  线程集合
    private HashSet<Worker> workers = new HashSet<>();

    //  核心线程数
    private int coreSize;

    //  获取任务超时时间
    private long timeout;

    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public void excute(Runnable task) {
        //  当任务数没有超过核心 coresize值.直接交给worker对象执行
        //  如果任务数超过  coresize,将任务加入任务队列暂存起来.
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增worker : {},  task:{}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                log.debug("加入队列:{}", task);
                //  taskQueue.put(task);
                //  这里设计上,因为需要多种情况处理,所以直接封装为一个接口,根据不同方式,调用,获得不同结果.
                //  1.死等
                //  2.带超时等待
                //  3.让调用者放弃任务执行.
                //  4.让调用者抛出异常.
                //  5.让调用者自己执行任务.
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapcity, RejectPolicy<Runnable> rejectPolicy) {

        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockingQueue<>(queueCapcity);
        this.rejectPolicy = rejectPolicy;

    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //  执行任务 
            //  1.当task不为空,执行任务.
            //  2.当task执行完毕,再接着从任务队列获取任务并执行.
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    log.debug(" running ...");
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("worker 被移除{}", this);
                workers.remove(this);
            }

        }
    }
}

@Slf4j(topic = "c.BlockingQueue")
class BlockingQueue<T> {
    public BlockingQueue(int capcity) {
        this.capcity = capcity;
    }

    //  1.任务队列表: " +"
    private Deque<T> queue = new ArrayDeque<T>();

    //  2.锁
    ReentrantLock lock = new ReentrantLock();

    // 3. 生产者变量.
    Condition fullWaitSet = lock.newCondition();

    //  4.消费者条件变量
    Condition emptyWaitSet = lock.newCondition();

    //  5.容量.
    private int capcity;

    //  6.阻塞获取.  带超时
    public T poll(Long timeout, TimeUnit unit) throws InterruptedException {

        long nanos = unit.toNanos(timeout);
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    if (nanos < 0) {
                        return null;
                    }
                    emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //  6.阻塞获取.
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = queue.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //  7.阻塞添加.
    public T put(T element) {
        lock.lock();
        try {
            while (queue.size() == capcity) {
                try {
                    log.debug("等待加入队列:{}", element);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    //  7.阻塞添加.  带超时时间
    public boolean offer(T task, long timeout, TimeUnit timeUnit) {
        lock.lock();
        try {
            long nanos = timeUnit.toNanos(timeout);
            while (queue.size() == capcity) {
                try {
                    log.debug("等待加入队列:{}", task);
                    if (nanos < 0) {
                        return false;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("加入任务队列", task);
            queue.addLast(task);
            emptyWaitSet.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    //   8.获取大小
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    //  
    public void tryPut(RejectPolicy<T> rejectPolicy, T task) {
        lock.lock();
        try {
            //  判断队列是否已满
            if (queue.size() == capcity) {
                rejectPolicy.reject(this, task);
            } else {
                log.debug("tryPut -> 加入任务队列:{}", task);
                queue.addLast(task);
                emptyWaitSet.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
