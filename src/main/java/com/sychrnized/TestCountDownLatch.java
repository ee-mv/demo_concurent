package com.sychrnized;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author micha
 */
@Slf4j(topic = "c.Test1")
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("latch count:{}", latch.getCount());
                latch.countDown();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("end:{}", latch.getCount());
            }
        }).start();

        latch.await();
        log.debug("main thread continue:{}", latch.getCount());
    }
}
