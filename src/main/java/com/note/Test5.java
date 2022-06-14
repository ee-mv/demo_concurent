package com.note;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "c.Test5")
public class Test5 {
    public static void main(String[] args) {


        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        log.debug(list.toString());
        for (Integer integer : list) {
            log.debug("result:{}", integer);

        }

        List<Integer> list2 = new ArrayList<Integer>();

        list2.forEach(System.out::print);
    }
}
