package com.futuretask;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestJoin")
public class TestJoin {

    static int r = 0 ;
    static int r1 = 0;
    static int r2 = 0;
    public static void main(String[] args) throws InterruptedException {

test2();

    }

    private static  void test2() throws InterruptedException {
        Thread t1 = new Thread(() ->
        {
            log.debug("t1 is running ! ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        }
        );

        Thread t2 = new Thread(()-> {
            log.debug("t2 is running !");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2=20;
        });

        long start = System.currentTimeMillis();

        t1.start();
        t2.start();

        log.debug("join begin");
        t1.join();
        log.debug("t1 join begin");

        t2.join();
        log.debug("t2 join end");

        long end = System.currentTimeMillis();

        log.debug("r1:{} r2:{} cost:{}", r1,r2,end-start);

    }

}
