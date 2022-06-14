package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author micha
 */
@Slf4j(topic = "c.Test011")
public class Test011 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {


        List<Thread> threads = new ArrayList<Thread>();
//        FutureTask<String> futuretask = new FutureTask<String>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                log.debug("result:{}", "futruretask result");
//                return "futruretask result";
//            }
//
//        });

        Thread t1 = new Thread(new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("result:{}", "futruretask result");
                return "futruretask result";
            }
        })
        );
        threads.add(t1);
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
            threads.get(i).join();
        }

        //t1.start();
        //t1.join();
        log.debug("end!");

    }
}
