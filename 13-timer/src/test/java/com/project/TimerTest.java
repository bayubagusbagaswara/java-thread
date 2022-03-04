package com.project;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    @Test
    void delayedJob() throws InterruptedException {

        // buat object timer task sebagai pekerjaan nya
        // timer task ini implement dari Runnable
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Delayed job");
                System.out.println("Thread: ["+Thread.currentThread().getName()+"]");
            }
        };

        // bikin object timer untuk thread
        var timer = new Timer();

        // gunakan scheduled untuk menundah task berapa lama
        // misal kita ingin menunda task selama 5 detik
        // jadi mirip seperti sleep 5 detik, baru akan menjalankan pekerjaannya
        timer.schedule(task, 5000);

        Thread.sleep(6000);
        System.out.println("Thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    void periodicJob() throws InterruptedException {

        var task = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Periodic job");
                System.out.println("Thread: ["+Thread.currentThread().getName()+"]");
            }
        };

        // create thread timer
        var timer = new Timer();

        // jadi task akan ditunda selama 2 detik diawal
        // lalu selanjutnya akan dijalankan tiap 2 detik sekali
        timer.schedule(task, 2000, 2000);

        Thread.sleep(10000);
        System.out.println("Thread: ["+Thread.currentThread().getName()+"]");
    }
}
