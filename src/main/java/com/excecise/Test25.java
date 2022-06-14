package com.excecise;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test25")
public class Test25 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            log.debug("in 临界区代码 ! ");
        } finally {
            lock.unlock();
        }
    }


}
