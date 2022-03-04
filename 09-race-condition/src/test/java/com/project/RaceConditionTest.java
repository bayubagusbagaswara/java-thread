package com.project;

import org.junit.jupiter.api.Test;

public class RaceConditionTest {

    @Test
    void counter() {
        // create object dari class Counter yang sudah kita buat
        var counter = new Counter();

        // bikin pekerjaan dari Runnable
        // misalnya kita buat pekerjaan sebanyak 1 juta
        // dan tiap pekerjaan akan mengubah (increment) data counter
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        // kita jalankan runnable nya secara sequential atau berurutan
        // misal kita run object runnable 3 kali
        // artinya nanti ada pekerjaan untuk mengubah data counter sebanyak 3 juta
        runnable.run();
        runnable.run();
        runnable.run();

        System.out.println("Counter: " + counter.getValue());
    }

    @Test
    void counterRunInThread() throws InterruptedException {
        var counter = new Counter();

        // bikin pekerjaan dari Runnable, dan lakukan increment counter
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        // kita jalankan Runnable didalam sebuah thread
        // tapi ada 3 thread berbeda yang menjalankan Runnable yang sama
        // harapan kita adalah data counter menjadi 3 juta
        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        // jalankan threadnya
        thread1.start();
        thread2.start();
        thread3.start();

        // kita tunggu semua thread nya selesai dijalankan
        thread1.join();
        thread2.join();
        thread3.join();

        // hasil counternya tidak sampai 3 juta
        // karena thread nya berebut menjalankan Runnable nya
        // Ini dinamakan problem Race Condition
        System.out.println("Counter: " + counter.getValue());
    }
}
