package com.n8;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j(topic = "c.TestTimer")
public class TestTimer {
    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask t1 = new TimerTask() {
            @Override
            public void run() {
                log.debug("t1");
            }
        };

        TimerTask t2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("t2");
            }
        };

        timer.schedule(t1, 1000);
        timer.schedule(t2, 1000);
        

    }
}
