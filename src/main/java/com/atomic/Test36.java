package com.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j(topic = "c.Test36")
public class Test36 {

    static AtomicReference<String> ref = new AtomicReference<String>("A");

    public static void main(String[] args) throws InterruptedException {


        //main 线程更改A  -> C
        other();

        Thread.sleep(2000);
        log.debug("change A  to C , :: {}", ref.compareAndSet("A", "C"));

        log.debug(ref.get());
    }

    static void other() {
        new Thread(() -> {
            log.debug("A -> B :: {}", ref.compareAndSet("A", "B"));
        }).start();
        new Thread(() -> {
            log.debug("B-> A :: {}", ref.compareAndSet("B", "A"));
        }).start();
    }
}
