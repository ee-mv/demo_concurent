package com.excecise;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test28")
public class Test28 {
    public final static Object room = new Object();

    static boolean hasCigrate = false;
    static boolean hasWaimai = false;

    static ReentrantLock ROOM = new ReentrantLock();
    //  烟休息室
    static Condition waiteCigrate = ROOM.newCondition();
    //  外卖休息室
    static Condition waiteWaimai = ROOM.newCondition();


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            ROOM.tryLock();

            try {
                log.debug("有烟没?没有就先歇会.:{}", hasCigrate);
                while (!hasCigrate) {
                    log.debug("没烟,先歇会.");
                    waiteCigrate.await();
                }
                log.debug("有烟,开始干活.");
                if (hasCigrate) {
                    log.debug("开始干活");
                    waiteCigrate.signal();
                } else {
                    log.debug("not finish work!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ROOM.unlock();
            }
        }, "t1");

        new Thread(() -> {
            log.debug("开始送烟");
            try {
                Thread.sleep(3000);
                while (!hasCigrate) {

                    hasCigrate = true;
                }
                log.debug("cigrante:{}", hasCigrate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "songyan").start();

        t1.start();

        new Thread(() -> {
            ROOM.lock();
            try {
                Thread.sleep(3000);
                log.debug("yan dao le!");
                waiteCigrate.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ROOM.unlock();
            }

        }, "松烟").start();

    }
}
