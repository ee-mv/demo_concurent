package com.futuretask;

public class TestFrames {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method1(10);
            }
        });

        t1.setName("t1");
        t1.start();


    }

    private static void method1(int x){
        int y = x+1;
        Object m = method2();
        System.out.println(m);
    }

    private static Object method2(){
        Object n = new Object();
        return  n;
    }

}
