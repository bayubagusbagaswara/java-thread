package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    void testCountDownLatch() throws InterruptedException {

        // bikin count down latch nya
        // misal jumlah counternya 5
        final var countDownLatch = new CountDownLatch(5);

        // bikin executor jumlahnya 10 thread
        final var executor = Executors.newFixedThreadPool(10);

        // lakukan perulangan sampai 5
        // artinya akan menjalankan 5 pekerjaan
        for (int i = 0; i < 5; i++) {
            int task = i;
            executor.execute(() -> {
                try {
                    System.out.println("Start Task: ["+task+"], from Thread: ["+Thread.currentThread().getName()+"]");
                    Thread.sleep(2000);
                    System.out.println("Finish Task: ["+task+"], from Thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // jika tasknya sudah selesai, maka turunkan counter nya
                    countDownLatch.countDown();
                }
            });
        }

        executor.execute(() -> {
            // kita ingin menunggu 5 task yang diatas selesai dieksekusi dulu
            try {
                countDownLatch.await();
                System.out.println("Finish All Task from Thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
