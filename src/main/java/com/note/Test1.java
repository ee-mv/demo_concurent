package com.note;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author micha
 * <p>
 * t1 5-> a
 * t2 5-> b
 * t3 5-> c
 * result:abcabcabcabcabc
 */
@Slf4j(topic = "c.Test1")
public class Test1 {

    private int flag;


    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);

        // a condition variable
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.printTest("a", a, b);
        }).start();
        new Thread(() -> {
            awaitSignal.printTest("b", b, c);
        }).start();
        new Thread(() -> {
            awaitSignal.printTest("c", c, a);
        }).start();
        System.out.println("\n");
        log.debug("end------------------");

        Thread.sleep(1000);
        awaitSignal.lock();
        try {
            a.signal();
        } finally {
            awaitSignal.unlock();
        }

    }


}

class AwaitSignal extends ReentrantLock {
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void printTest(String str, Condition current, Condition next) {

        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                current.await();
                System.out.print(str);
                next.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }
        }

    }
}
