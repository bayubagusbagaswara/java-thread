package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void mainThread() {
        // kita lihat thread saat ini yang berjalan
        // di unit test juga akan dibuatkan otomatis thread nya
        String name = Thread.currentThread().getName();
        System.out.println("Thread name: " + name);
    }
}
