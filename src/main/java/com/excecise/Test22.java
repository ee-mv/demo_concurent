package com.excecise;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test22")
public class Test22 {


    public static void main(String[] args) {
        BigRoom bigroom = new BigRoom();
        new Thread(() -> {
            try {
                bigroom.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1-sleep").start();

        new Thread(() -> {
            try {
                bigroom.study();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2-study").start();


    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom {
    private static final Object sleepRoom = new BigRoom();
    private static final Object studyRoom = new BigRoom();

    public void sleep() throws InterruptedException {
        synchronized (sleepRoom) {
            log.debug("sleep 6 小时");
            Thread.sleep(6000);
            log.debug("sleep end");
        }
    }


    public void study() throws InterruptedException {
        synchronized (studyRoom) {
            log.debug("study 3 小时");
            Thread.sleep(3000);
            log.debug("study end");
        }
    }

}
