package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void threadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                // kita sleep runnable nya selama 2 detik dulu
                Thread.sleep(2_000L);
                System.out.println("Program other from Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // create thread dan masukkan object runnable
        var thread = new Thread(runnable);
        thread.start();

        // karena ini asynchronous, maka akan mengerjakan kode program yang bawah dulu
        // dikarenakan thread runnablenya sleep 2 detik
        // sehingga keburu selesai kode program di thread main atau unit test ini
        System.out.println("Program finish from thread: " + Thread.currentThread().getName());

        // caranya agar tetap menunggu thread yang menjalankan object runnable selesai, maka kita sleep juga thread main nya
        // tapi dengan waktu sleep lebih banyak daripada waktu sleep di thread runnable
        Thread.sleep(3_000L);
    }
}
