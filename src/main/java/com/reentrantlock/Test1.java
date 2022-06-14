package com.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author micha
 */
@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "futuretask result !";
            }
        });
        
        Thread t1 = new Thread(future);

        t1.start();
        t1.join();
        log.debug("result:{}", future.get());

    }
}
