package com.sychrnized;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test17")
public class Test17 {

    volatile static int counter = 0;
    volatile static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    counter++;
                }
            }
            log.debug("{}", counter);
        }, "t1");


        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5000; i++) {
                    counter++;
                }
            }
            log.debug("counter-t2:{}", counter);
        }, "t2");

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        Thread.currentThread().setName("i am main thread");
        log.debug("counter:{}", counter);


    }
}
