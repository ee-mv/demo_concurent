package com.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j(topic = "c.TestThreadSafe")
public class TestThreadSafe {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 100;

    public static void main(String[] args) {
        ThreadUnSafe test = new ThreadUnSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + (i + 1)).start();
        }

    }
}

class ThreadUnSafe {
    ArrayList<String> array = new ArrayList<>();

    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            method2(array);
            method3(array);
        }
    }

    private void method2(ArrayList<String> list) {
        list.add("a");
    }

    private void method3(ArrayList<String> list) {
        list.remove(0);
    }

}
