package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.Test8")
public class Test8 {
    public static void main(String[] args) throws InterruptedException {

        log.debug("enter");
        TimeUnit.SECONDS.sleep(3);
        log.debug("end");

    }
}
