package com.sychrnized;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * @author micha
 */
@Slf4j(topic = "c.TestStampLock")
public class TestStampLock {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        //  StampedLock  可以增加只读多线程的性能.  加解锁,增加一个戳概念.
        StampedLock rwLock = new StampedLock();

        long stampLock = rwLock.readLock();

        //  信号量/  Semaphore  共享资源可以被几个线程共享使用
        Semaphore s = new Semaphore(1);
        rwLock.readLock();
        try {

            for (int i = 0; i < 5; i++) {

                new Thread(() -> {
                    try {
                        s.acquire();
                        log.debug("count result:{}", count++);
                        Thread.sleep(2000);
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            }

        } finally {
            rwLock.unlock(stampLock);
        }


    }
}
