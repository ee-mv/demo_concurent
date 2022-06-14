package com.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test02")
public class Test02 {
    public static void main(String[] args) {

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                log.debug("Test02-2 thread running ! ");
            }
        };

        Runnable runnable = () -> log.debug("Test02 thread");

        Thread t2 =new Thread(runnable, "ThreadName:Test02-2");
        Thread t =new Thread(runnable, "ThreadName:Test02");
        t2.start();
        t.start();

        System.out.println("MainThreadName:main is running");


    }
}
