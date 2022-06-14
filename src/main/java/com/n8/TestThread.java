package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j(topic = "c.TestThread")
public class TestThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("running");
                Thread.sleep(1000);
                return "ok";
            }
        });

        log.debug("future:{}", future.get());
    }
}
