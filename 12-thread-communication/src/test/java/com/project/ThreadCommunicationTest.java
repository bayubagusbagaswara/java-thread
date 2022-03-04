package com.project;

import org.junit.jupiter.api.Test;

public class ThreadCommunicationTest {

    private String message = null;

    @Test
    void manual() throws InterruptedException {

        // misal dengan cara manual untuk sharing variable
        var thread1 = new Thread(() -> {
            // kita lakukan iterasi terus menerus sampai data message nya ada
            while (message == null) {
                // kita akan tunggu, sampai diisi messagenya
                // data message akan diisi oleh thread2
            }
            // jika message sudah diisi, baru kita print out messagenya apa
            System.out.println(message);
        });

        // kita buat thread2, untuk mengisi nilai ke variable message
        var thread2 = new Thread(() -> {
            message = "Bayu Bagus Bagaswara";
        });

        // jalankan thread2, makan thread2 akan mengisi variable message
        thread2.setName("Thread-2");
        thread2.start();


        // kita jalankan thread1, disini message sudah ada isinya
        // jadi thread1 bergantung terhadap thread2 untuk mendapatkan nilai message
        thread1.setName("Thread-1");
        thread1.start();

        // tunggu semua thread selesai
        thread2.join();
        thread1.join();

        System.out.println("Program finish from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    void waitNotify() throws InterruptedException {
        // kita buat dulu object, gunanya hanya untuk lock (dengan memanggil method wait())
        final var lock = new Object();

        var thread1 = new Thread(() -> {
            // disini kita lock thread1 dengan cara wait()
            // sampai ada orang (thread lain) yang melakukan notify()
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                    System.out.println("This is thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // misal thread2 ini yang melakukan notify()
        var thread2 = new Thread(() -> {
            synchronized (lock) {
                // lakukan lock dulu
                message = "Bayu Bagus Bagaswara";
                // setelah di notify, maka thread2 akan memberitahu thread1 agar bisa menjalankan tugasnya
                lock.notify();
                System.out.println("This is thread: ["+Thread.currentThread().getName()+"]");
            }
        });

        // perlu diingat, bahwa thread yang menggunakan wait() harus dijalankan terlebih dahulu
        // jadi kita jalankan dulu thread1
        thread1.setName("Thread-1");
        thread1.start();
        thread2.setName("Thread-2");
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    void waitNotifyAll() throws InterruptedException {
        final var lock = new Object();

        // thread1 akan melakukan wait()
        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                    System.out.println("This is thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // thread3, tugasnya sama yakni melakukan wait()
        var thread3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                    System.out.println("This is thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // misal thread2 ini yang melakukan notify()
        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Bayu Bagus Bagaswara";
                // ganti notifyAll agar bisa memberitahu ke banyak thread yakni thread1 dan thread3
                lock.notifyAll();
                System.out.println("This is thread: ["+Thread.currentThread().getName()+"]");
            }
        });

        thread1.setName("Thread-1");
        thread1.start();

        thread3.setName("Thread-3");
        thread3.start();

        thread2.setName("Thread-2");
        thread2.start();

        thread1.join();
        thread3.join();
        thread2.join();

        System.out.println("This is thread: ["+Thread.currentThread().getName()+"]");
    }

}
