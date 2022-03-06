package com.project;

import org.junit.jupiter.api.Test;

public class AtomicTest {

    @Test
    void counter() throws InterruptedException {

        // create variable counter
        var counter = new CounterAtomic();

        // create runnable untuk task
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
        };

        // jalankan task sebanyak 3 kali di thread yang berbeda,
        // dan harusnya hasilnya adalah 3 juta
        // artinya sudah thread safe, dan tidak terjadi race condition
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        // jalankan semua thread
        thread1.setName("Thread-1");
        thread1.start();

        thread2.setName("Thread-2");
        thread2.start();

        thread3.setName("Thread-3");
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Counter: " + counter.getValue());
    }
}
