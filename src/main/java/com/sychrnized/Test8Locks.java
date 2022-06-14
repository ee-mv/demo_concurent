package com.sychrnized;

import lombok.extern.slf4j.Slf4j;

/**
 * @author micha
 */
@Slf4j(topic = "c.Test8Locks")
public class Test8Locks {
    public static void main(String[] args) {

        Number n1 = new Number();

        new Thread(() -> {
            log.debug("begin");
            n1.a();
        }).start();

        try {
            new Thread(() -> {
                log.error("begin");
                n1.b();
            }).start();
        } finally {
            log.error("end it!");
        }


    }
}


@Slf4j(topic = "c.Number")
class Number {
    public Number() {
    }

    public synchronized void a() {
        log.debug("a:{}", "1");
    }

    public synchronized void b() {
        log.debug("b:{}", "2");
    }


}