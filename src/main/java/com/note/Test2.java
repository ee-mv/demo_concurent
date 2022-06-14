package com.note;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test2")
public class Test2 {
    // volatile 易变
    static boolean boo = true;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (boo) {
                //  log.debug("start running");
            }
        }, "t1").start();


        Thread.currentThread().sleep(10000);
        log.debug("stop t1");

        boo = false;
    }
}
