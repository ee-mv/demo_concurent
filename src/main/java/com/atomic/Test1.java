package com.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(0);

        log.debug("Atomic value = {}", i.getAndIncrement());
        log.debug("Atomic value = {}", i.getAndIncrement());

        log.debug(i.toString());

        log.debug("Atomic value = {}", i.addAndGet(5));

    }
}
