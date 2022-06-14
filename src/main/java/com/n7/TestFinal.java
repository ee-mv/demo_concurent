package com.n7;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestFinal")
public class TestFinal {

    static final int A = 10;
    static final int B = Short.MAX_VALUE + 1;
    final int a = 20;
    final int b = Integer.MAX_VALUE;

    final void test1() {

    }

    public static void main(String[] args) {

    }
}

class UseFinal {
    public void test() {
        System.out.println(TestFinal.A);
        System.out.println(TestFinal.B);
        System.out.println(new TestFinal().a);
        System.out.println(new TestFinal().b);
        new TestFinal().test1();
    }
}

class UseFinal2 {
    public void test() {
        System.out.println(TestFinal.A);
    }
}