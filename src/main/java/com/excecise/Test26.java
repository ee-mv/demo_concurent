package com.excecise;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test26")
public class Test26 {

    public static final Object obj = new Test26();


    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        boolean targetBoolean = false;

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                log.debug("t1.start");
            }

            reentrantLock.tryLock();
            try {

                
            } finally {
                reentrantLock.unlock();
            }

        }, "t1");


        t1.start();
    }
}
