package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic="c.Test14")
public class Test14 {
    public static void main(String[] args) throws InterruptedException {
        test3();
    }


    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("start park");
            LockSupport.park();
            log.debug("unpark");
            log.debug("打断状态:{}",Thread.currentThread().isInterrupted());
        },"t1");

        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
