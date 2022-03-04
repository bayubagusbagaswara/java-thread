package com.project;

import org.junit.jupiter.api.Test;

public class SynchronizationTest {

    @Test
    void counter() throws InterruptedException {

        var counter = new SynchronizedCounter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        // Ini sudah tidak terjadi Race Condition
        System.out.println("Counter: " + counter.getValue());
    }

    @Test
    void counterSynchronizedStatement() throws InterruptedException {

        var counter = new SynchronizedStatementCounter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        // tetap 3_000_000
        // karena kita synchronized kode program value++
        System.out.println("Counter: " + counter.getValue());
    }
}
