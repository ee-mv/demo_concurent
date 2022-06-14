package com.excecise;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test21")
public class Test21 {

    public static void main(String[] args) {

    }
}

//  消息队列,java线程间通信.
class MessageQueue {
    //  获取消息方法
    public Message take() {

        return null;
    }

    //  存入消息方法
    public void put() {
    }

}

final class Message {
    private int id;
    private Object value;

    Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}