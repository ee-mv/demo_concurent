package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

@Slf4j(topic = "c.TestForkJoin")
public class TestForkJoin {
    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new MyTask(5)));

    }
}

@Slf4j(topic = "c.MyTask")
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1) {
            log.debug("join() {}", n);
            return 1;
        }
        MyTask t1 = new MyTask(n - 1);
        t1.fork(); //  让一个线程去执行这个任务.

        log.debug("join() {} + {}", n, t1);

        int result = n + t1.join();  // 获取任务结束.

        log.debug("join() {} + {} = {}", n, t1, result);

        return result;
    }
}