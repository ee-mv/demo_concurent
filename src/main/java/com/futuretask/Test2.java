package com.futuretask;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author micha
 */
@Slf4j(topic = "Test2")
public class Test2<T> implements Serializable {
    private static final Object SS = "111";
    public String t1 = (String) "123";


    public T getT1() {
        return (T) SS;
    }
}
