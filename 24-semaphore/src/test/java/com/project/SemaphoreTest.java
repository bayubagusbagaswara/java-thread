package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    @Test
    void testSemaphore() throws InterruptedException {

        // buat semaphore
        // misal yang diperbolehkan hanya 1 doang,
        // jadi task/pekerjaan akan dieksekusi satu per satu
        final var semaphore = new Semaphore(1);

        // bikin executors, bikin 100 thread
        final var executor = Executors.newFixedThreadPool(100);

        // bikin perulangan
        for (int i = 0; i < 100; i++) {

            // kita masukkan task dan jalankan
            executor.execute(() -> {
                try {
                    // acquire semaphore, untuk memastikan dia boleh menaikkan counternya atau tidak
                    // 100 thread akan menunggu antri disini, sampai ada thread yang release semaphore nya
                    // karena kita permits semaphorenya hanya 1, mkaa keluarnya akan satu per satu
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("Finish from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release semaphorenya
                    semaphore.release();
                }
            });
        }
        executor.awaitTermination(2, TimeUnit.MINUTES);
    }
}