package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;

@Slf4j(topic = "c.TestThread")
public class TestThread {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                // FileReader.read(Constants.MP4)
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        System.out.println(t1.getState());
        t1.start();
        log.debug("t1.status = " + t1.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("t1.status = " + t1.getState());
    }
}
