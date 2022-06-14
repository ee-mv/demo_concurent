package com.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j(topic = "c.TestStream")
public class TestStream {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello.com", "xs03.cn", "8.com", "123.com");
        log.debug("before deal list:{}", list.toString());

        //stream deal with 
        List listResult = list.stream().parallel().filter((e) -> e.contains("o"))
                .filter((e) -> e.length() > 6)
                .map(i -> i + ".cn")
                .sorted()
                .collect(Collectors.toList());

        long count = 0;
        count = list.stream().filter((e) -> e.length() > 6).count();

        log.debug("end deal with list:{}", listResult.toString());
        log.debug("list count = {}", count);

        Random random = new Random(100);
        random.ints().limit(1).forEach(System.out::println);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        // 获取空字符串的数量
        long count2 = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count2);
    }
}
