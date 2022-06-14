package com.futuretask;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test7")
public class Test7 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("enter sleeping");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up...");
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        Thread.sleep(1000);
        log.debug("interuppt...");
        t1.interrupt();
    }
}
