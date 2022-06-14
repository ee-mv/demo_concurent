package com.excecise;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test19")
public class Test19 {

    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁...");
                try {
                    //  Thread.sleep(20000);
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        Thread.sleep(1000);
        synchronized (lock) {
            log.debug("获得锁");
        }

    }
}
