package com.excecise;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test27")
public class Test27 {
    static final ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {

        //  创建2个条件变量的休息室
        Condition cigrateCondition = lock.newCondition();
        Condition sleepCondition = lock.newCondition();

        boolean cigrate = false;
        boolean sleep = false;

        cigrateCondition.await();

        Thread.currentThread().sleep(2000);

        cigrateCondition.signal();

    }
}
