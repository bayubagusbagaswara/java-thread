package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadLocalRandomTest {

    @Test
    void testThreadLocalRandom() throws InterruptedException {

        final ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    // buat thread local random
                    int number = ThreadLocalRandom.current().nextInt();
                    System.out.println("Number: ["+number+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    void testRandomStream() throws InterruptedException {

        // buat executor threadpool
        final var executor = Executors.newFixedThreadPool(10);

        // execute task
        executor.execute(() -> {
            // ints() artinya tidak ada batasan randomnya atau unlimiteds, atau kalau perulangan maka tidak akan pernah berhenti
            // bisa juga menentukan sendiri minimal dan maksimalnya
            ThreadLocalRandom.current().ints(100, 0, 1000)
                    // cetak nilai randomnya
                    .forEach(number -> {
                        System.out.println("Number: ["+number+"], from thread: ["+Thread.currentThread().getName()+"]");
                    });
        });

        // executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }
}