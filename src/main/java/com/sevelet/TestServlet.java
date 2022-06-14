package com.sevelet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestServlet extends HttpServlet {
    Map<String, Object> map = new HashMap<>();

    String s1 = "...";
    final String s2 = "...";

    static Date d1 = new Date();

    final Date d2 = new Date();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) {
        // use the param
    }
}
