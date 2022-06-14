package com.sevelet;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) {
        TestServlet tests = new TestServlet();
        final Date d1 = TestServlet.d1;
        System.out.println(d1);
    }
}
