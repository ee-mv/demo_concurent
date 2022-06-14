package com.note;

public class TestVolatile {
    volatile boolean initialized = false;

    synchronized void init() {
        if (initialized) {
            return;
        }
        doInit();
        initialized = true;
    }

    void doInit() {

    }
}
