package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author micha
 */
@Slf4j(topic = "c.TestConcurentThread")
public class TestConcurentThread {

    public static void main(String[] args) {

        FutureTask task = new FutureTask(new Callable() {
            @Override
            public String call() throws Exception {
                return "ok";
            }
        });

        //  ThreadPoolExecutor executor = new ThreadPoolExecutor();
        Executor executor = Executors.newFixedThreadPool(10);
        executor.execute(task);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            log.debug("start debug!");
        } finally {
            lock.unlock();
        }


    }
}
