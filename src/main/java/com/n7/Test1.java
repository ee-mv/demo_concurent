package com.n7;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Slf4j(topic = "c.Test1")
public class Test1 {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        System.out.println(simpleDateFormat.parse("1921-10-22"));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(new BigDecimal(1));
        System.out.println(dateTimeFormatter.parse("1999-10-11"));
    }
}
