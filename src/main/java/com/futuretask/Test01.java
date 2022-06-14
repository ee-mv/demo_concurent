package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test01")
public class Test01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futuretask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running");
                return 100;
            }
        });

        Thread t = new Thread(futuretask, "futuretask t1");

        t.start();

        log.debug("t:{}", futuretask.get());

        List<Integer> list = new ArrayList<>();
        list.add(1);
        log.debug("list:{}", list.toString());

        Test2<String> t2 = new Test2<>();
        log.debug("t2:{}", t2.getT1());
    }
}
