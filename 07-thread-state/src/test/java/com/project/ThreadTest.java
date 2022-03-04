package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void threadState() throws InterruptedException {

        // Mendapatkan informasi thread menggunakan State

        // buat thread dan buat pekerjaan untuk thread nya
        var thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // State thread = RUNNABLE artinya thread sedang berjalan melakukan pekerjaan
                System.out.println("State thread: ["+Thread.currentThread().getState()+"]");
                System.out.println("Program other from thread: ["+Thread.currentThread().getName()+"]");
            }
        });

        thread.setName("Bayu-Thread");
        // State thread = NEW artinya thread selesai dibuat
        System.out.println("State thread: ["+thread.getState()+"]");

        // jalankan thread
        thread.start();
        // join thread
        thread.join();
        // State thread = THREAD TERMINATED artinya thread selesai berjalan
        System.out.println("State thread: ["+thread.getState()+"]");
        System.out.println("Program finish from thread: ["+Thread.currentThread().getName()+"]");
    }
}
