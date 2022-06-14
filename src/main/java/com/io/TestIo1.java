package com.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j(topic = "c.TestIo1")
public class TestIo1 {
    public static void main(String[] args) throws IOException {

        File file = new File("Hello1.txt");
        // 创建文件
        file.createNewFile();
        // creates a FileWriter Object
        // D:\ProgramFilesJAVA\workspace\demo_concurent\Hello1.txt
        FileWriter writer = new FileWriter(file);
        // 向文件写入内容
        writer.write("This\n is\n an\n example\n");
        writer.flush();
        writer.close();
        // 创建 FileReader 对象
        FileReader fr = new FileReader(file);
        char[] a = new char[40];
        fr.read(a); // 读取数组中的内容
        for (char c : a) {
            System.out.print(c); // 一个一个打印字符
        }
        fr.close();


    }
}
