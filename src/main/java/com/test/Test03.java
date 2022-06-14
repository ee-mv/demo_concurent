package com.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test03")
public class Test03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        return 300;
                    }
                }
        );

        new Thread(task, "t3").start();

        log.debug("result: " + task.get());

    }
}
