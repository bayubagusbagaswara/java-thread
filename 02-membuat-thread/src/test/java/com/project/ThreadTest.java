package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void createThread() {
        // kita akan bikin thread, dimana akan ada 2 thread yang berjalan asynchronous

        // Langkah Pertama, kita buat object Runnable nya
        // kita bukan pekerjaan untuk thread dengan Runnable
        // jadi object Runnable ini yang akan dijalankan oleh Thread yang kita buat
        Runnable runnable = () -> {
            System.out.println("Program other from Thread: " + Thread.currentThread().getName());
        };

        // Langkah Kedua, kita buat thread nya
        // lalu masukkan object Runnable nya kedalam Thread
        var thread = new Thread(runnable);

        // Langkah Ketiga, kita jalankan thread nya, denganmemanggil method start()
        // ketika start, maka akan berjalan secara asynchronous antara 2 Thread, yakni Thread Main dan Thread-0 yang kita buat
        // biasanya ini tiap komputer berbeda hasilnya, tergantung kecepatan dalam menjalankan threadnya
        // bisa Thread Main selesai duluan, atau Thread-0 yang selesai duluan
        // atau kadang-kadang malah tidak keluar salah satunya
        thread.start();

        // kode program thread main
        System.out.println("Program finish from Thread: " + Thread.currentThread().getName());
    }
}
