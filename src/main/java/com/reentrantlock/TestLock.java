package com.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.TestLock")
public class TestLock {
    public static int count = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            for (int i = 0; i < 3000; i++) {
                count++;
            }

            log.debug("count result : {}", count);
        } finally {
            lock.unlock();
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3000; i++) {
                    count++;
                }
                log.debug("count result2 = {}", count);
            }
        });
        
        t1.start();


    }
}
