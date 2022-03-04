package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void threadJoin() throws InterruptedException {

        // Thread Join untuk menunggu thread yang lain sampai selesai menjalankan tugasnya
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000L);
                System.out.println("Program other from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // create thread dan jalankan thread nya
        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Pending... from thread: " + Thread.currentThread().getName());

        // kita ganti dengan join
        // artinya thread kedua akan dijoin dengan thread utama
        // sehingga thread utama akan menunggu thread kedua selesai menjalankan tugasnya
        thread.join();

        System.out.println("Program finish from thread: " + Thread.currentThread().getName());
    }
}
