package com.test;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test01")
public class Test01 {
    public static void main(String[] args) {

        Thread t = new Thread(() -> log.debug("Test01 is running ! "));

        t.setName("ThreadName:Test01");
        t.start();

        log.debug("Test01  method main is running !");

    }
}
