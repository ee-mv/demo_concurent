package com.sychrnized;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author micha
 */
@Slf4j(topic = "c.ClassContainer")
public class ClassContainer {
    private int data;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) {


    }
}
