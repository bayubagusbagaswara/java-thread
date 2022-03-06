package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    @Test
    void countDownLatchWithPhaser() throws InterruptedException {

        // bikin phaser nya dulu
        final var phaser = new Phaser();

        // bikin executor threadpool, misalnya kita bikin thread 10
        final var executor = Executors.newFixedThreadPool(10);

        // kita bisa registrasi atau menambah counter phasernya
        phaser.bulkRegister(5);

        // bikin iterasi untuk task threadnya
        for (int i = 0; i < 5; i++) {
            int task = i;
            executor.execute(() -> {
                try {
                    System.out.println("Start Task: ["+task+"], from Thread: ["+Thread.currentThread().getName()+"]");
                    Thread.sleep(2000);
                    System.out.println("End Task: ["+task+"], from Thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // ini untuk menurunkan counternya
                    phaser.arrive();
                }
            });
        }

        // kita bikin thread untuk menunggu
        // mau nunggu sampai berapa arrive nya
        executor.execute(() -> {
            // kita tunggu sampe phasernya 0
            phaser.awaitAdvance(0);
            System.out.println("All tasks done");
        });

        executor.awaitTermination(30, TimeUnit.SECONDS);
    }

    @Test
    void cyclicBarrierWithPhaser() throws InterruptedException {

        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);

        for (int i = 0; i < 5; i++) {
            int task = i;
            executor.execute(() -> {
                // tunggu sampai dia 0, baru jalan semua
                phaser.arriveAndAwaitAdvance();
                System.out.println("Task: " + task + " done");
            });
        }
        executor.awaitTermination(30, TimeUnit.SECONDS);
    }
}
