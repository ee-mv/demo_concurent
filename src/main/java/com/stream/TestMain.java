package com.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j(topic = "c.TestMain")
public class TestMain {
    public static void main(String[] args) {
        //  list to map
        List<String> list = Arrays.asList("1.com", "2.com", "3.com");
        Map<Integer, Object> map = new HashMap<>();

        map = list.stream()
                .limit(2)
                .collect(Collectors.toMap(str -> list.indexOf(str), str -> str));

        map.forEach((key, value) -> {
            System.out.println(key + "::" + value);
        });

    }
}
