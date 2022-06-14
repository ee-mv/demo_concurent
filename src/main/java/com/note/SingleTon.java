package com.note;

import lombok.extern.slf4j.Slf4j;

/**
 * @author micha
 */
@Slf4j(topic = "c.SingleTon")
public class SingleTon {
    SingleTon() {
    }

    private static volatile SingleTon singleton = null;

    public static SingleTon getInstance() {
        if (singleton == null) {
            synchronized (SingleTon.class) {
                if (singleton == null) {
                    singleton = new SingleTon();
                }
            }
        }
        return singleton;
    }

}
