package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    @Test
    void testCyclicBarrier() {

        // kita tentukan cyclic barriernya 5
        final var cyclicBarrier = new CyclicBarrier(5);
        // buat thread 10
        final var executor = Executors.newFixedThreadPool(10);

        // perulangan untuk menjalankan task
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Menunggu.... from Thread: ["+Thread.currentThread().getName()+"]");
                    // setelah 5 thread dijalankan, maka baru dilepas await nya
                    // jika kita hanya bikin 4 thread, maka tidak akan di lepas awaitnya, jadi bakal tetap menunggu terus
                    cyclicBarrier.await();
                    System.out.println("Selesai Menunggu from Thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
