package com.all;

import lombok.extern.slf4j.Slf4j;

/**
 * @author micha
 * 课程:  黑马程序员全面深入学习Java并发编程，JUC并发编程全套教程
 * 总结:
 * 1:synchronized 原理.
 * 2:锁对象,锁线程.
 * 3:monitor,管程,监视器.
 * 4:CAS+volatile,  乐观无锁并发.解决效率问题,适用场景:线程少,竞争低.
 * 4.1:原子性,用原子类处理.
 * 4.2:要增加自旋.减少上下文切换.合理安排二次检查,解决原子性.
 * 5:ReentrantLock+ReentrantReadWriteLock,可重入锁.  读写分离管理.
 * 6:StampedLock,  只有读线程,这个工具类效率最高.因为加解锁的时候增加了戳概念.
 * 7:线程池:ScheduledPoolExecutor
 * 7.1:countDownLatch,替代join功能.
 */
@Slf4j(topic = "c.Note")
public class Note {
    public static void main(String[] args) {

        final Note note = new Note();

        note.printFull();
    }

    public void printFull() {

    }
}
