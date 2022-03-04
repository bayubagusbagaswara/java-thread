package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void threadName() {
        // Mengubah nama thread

        // buat thread dan buat pekerjaan sekaligus didalam thread nya
        var thread = new Thread(() -> {
            System.out.println("Program other from thread: ["+Thread.currentThread().getName()+"]");
        });

        // kita bisa ubah nama thread nya, dengan menggunkan setName()
        thread.setName("Bayu-Thread");

        // jalankan threadnya, maka thread akan mulai menjalankan pekerjaannya
        thread.start();

        System.out.println("Program finish from thread: ["+Thread.currentThread().getName()+"]");
    }
}
