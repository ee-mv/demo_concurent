package com.excecise;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test23")
public class Test23 {
    public static void main(String[] args) {

        final Object A = new Test23();
        final Object B = new Test23();

        Thread t1 = new Thread(() -> {
            synchronized (A) {
                log.debug("lock-A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    log.debug("lock-A-B");
                    try {
                        Thread.sleep(Long.parseLong("10000"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("end");
                }
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            synchronized (B) {
                log.debug("lock-B");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    log.debug("lock-B-A");
                    try {
                        Thread.sleep(Long.parseLong("20000"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.debug("end");
                }
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
