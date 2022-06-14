//package com.excecise;
//
//import lombok.extern.slf4j.Slf4j;
//import org.openjdk.jol.info.ClassLayout;
//
//@Slf4j(topic = "c.TestBiased")
//public class TestBiased {
//    public static void main(String[] args) throws InterruptedException {
//        Dog dog = new Dog();
//        log.debug("dog:{}", ClassLayout.parseInstance(dog).toPrintable(dog
//        ));
//
//        Thread.currentThread().setName("my main");
//        Thread.sleep(3000);
//        log.debug("dog-2:{}", ClassLayout.parseInstance(new Dog()).toPrintable(new Dog()
//        ));
//
//    }
//}
//
//class Dog {
//
//}
//
//class InstanceExample {
//    public static final Object INSTANCE = new Object();
//
//}
