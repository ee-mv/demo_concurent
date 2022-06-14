package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.Test9")
public class Test9 {

    static int r= 0 ;
    public static void main(String[] args)  throws InterruptedException{

        test1();

    }

    private static void test1() throws InterruptedException {
        log.debug("start");
        Thread t1 = new Thread(() -> {
                log.debug("Thread t1 is running");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("end");
                r=10;
        },"t1");

        t1.start();
        t1.join();

        log.debug("result={}" , r);
        log.debug("end");
    }
}
