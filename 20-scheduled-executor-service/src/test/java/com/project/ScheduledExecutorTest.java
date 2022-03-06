package com.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    @Test
    @DisplayName("Delayed Job")
    void delayedJob() throws InterruptedException {

        // misal Threadpool nya ada 10
        var executor = Executors.newScheduledThreadPool(10);

        // create data future
        // misal setelah 5 detik kita akan mengisi data ke future
        var future = executor.schedule(() -> {
            System.out.println("Hello Scheduled after 5 second from Thread: ["+Thread.currentThread().getName()+"]");
        }, 5, TimeUnit.SECONDS);

        // jika ingin tahu berapa lama lagi akan dieksekusi
        System.out.println("How many time again... ["+future.getDelay(TimeUnit.MILLISECONDS)+"]");

        executor.awaitTermination(30, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Periodic Job")
    void periodicJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);

        // tiap 2 detik kita akan mengirim data ke future
        var future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello Scheduled, from Thread: ["+Thread.currentThread().getName()+"]");
        }, 2, 2, TimeUnit.SECONDS);

        System.out.println("How many time again... ["+future.getDelay(TimeUnit.MILLISECONDS)+"]");

        executor.awaitTermination(30, TimeUnit.SECONDS);
    }
}