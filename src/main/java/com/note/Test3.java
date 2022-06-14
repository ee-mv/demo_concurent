package com.note;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test3")
public class Test3 {
    public static void main(String[] args) {
        SingleTon singleton = SingleTon.getInstance();

        log.debug("singtong instance:{}", singleton.toString());
    }
}
