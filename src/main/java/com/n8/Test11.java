package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "c.Test11")
public class Test11 {
    public static void main(String[] args) {
        //Executors.newSingleThreadExecutor().execute
        // Executors.newCachedThreadPool();
        //  Executors.newFixedThreadPool()
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "my_pool_t" + t.getAndIncrement());
            }
        });

        //   private AtomicInteger t = new AtomicInteger(0);
        pool.execute(() -> {
            log.debug("1");
        });
        pool.execute(() -> {
            log.debug("2");
        });
        pool.execute(() -> {
            log.debug("3");
        });

        pool.shutdown();

    }
}
