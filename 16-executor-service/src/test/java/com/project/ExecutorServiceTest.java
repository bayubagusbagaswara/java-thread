package com.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

    @Test
    @DisplayName("Executor Single Thread")
    void testExecutorService() throws InterruptedException {

        // kita bikin executors
        // misal hanya ingin satu thread saja, maka gunakan newSingleThread
        // executors ini untuk mengeksekusi Runnable
        // jadi task kita akan dijalankan satu thread saja
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // buat task sebanyak 20 task
        for (int i = 0; i < 20; i++) {
            int task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable task: ["+task+"] in thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // executor.shutdown();
        // kita tunggu sampai semua task selesai dikerjakan
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Thread finish: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Executor Thread Fix")
    void testExecutorServiceFix() throws InterruptedException {

        // kita bikin executors
        // misal kita ingin threadnya fix jumlahnya (minimal dan maksimalnya)
        // kita buat jumlah thread nya adalah 10
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // jumlah minimal dan maksimal threadnya 10
        // misal kita bikin task sebanyak 100
        // sehingga tiap thread akan mengeksekusi 10 task, karena 100 task dibagi dalam 10 thread
        // tiap thread hanya menjalankan 10 task
        // jadi hanya diperlukan waktu sekitar 10 detik untuk menyelesaikan semua task nya
        for (int i = 0; i < 100; i++) {
            int task = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Runnable task: ["+task+"] in thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Thread finish: ["+Thread.currentThread().getName()+"]");
    }
}
