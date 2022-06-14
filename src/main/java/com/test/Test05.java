package com.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test05")
public class Test05 {
    public static void main(String[] args) {


        new Thread(
                () -> {while (true) {
            log.debug("running");
        }},"t1"
        ).start();

        new Thread(
                () -> {while (true) {
                    log.debug("running");
                }},"t2"
        ).start();

    }
}
