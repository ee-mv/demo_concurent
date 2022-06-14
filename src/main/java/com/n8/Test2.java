package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) throws Exception {


        MyThread2 my = new MyThread2();

        System.out.println(my.call());

        FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });

        Thread t = new Thread(future, "future t1");
        t.start();
        System.out.println(future.get());

        for (; ; ) {
            System.out.println("1");
        }
    }
}

class MyThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        return "ok";
    }
}
