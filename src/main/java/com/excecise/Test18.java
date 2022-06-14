package com.excecise;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test18")
public class Test18 {
    static final Object obj = new Object();
    
    private int id;

    public Test18(int id) {
        this.id = id;
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行...");
                try {
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("执行-其他代码...");
            }
        }, "t1").start();


        new Thread(() -> {
            synchronized (obj) {
                log.debug("执行...");
                try {
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("执行-其他代码...");
            }
        }, "t2").start();


        Thread.currentThread().sleep(6000);
        log.debug("唤醒线程");


        synchronized (obj) {
            obj.notify();
            //obj.notifyAll();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
