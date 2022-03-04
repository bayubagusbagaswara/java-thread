package com.project;

import org.junit.jupiter.api.*;

public class ThreadTest {

    @Test
    void threadInterrupt() throws InterruptedException {
        // Thread Interrupt untuk menghentikan thread yang sedang berjalan, biasanya digunakan untuk menghentikan suatu tugas di thread, lalu pindah tugas di thread lain
        // misal kita buat 10 kali pekerjaan
        // lalu tiap pekerjaan kita sleep 1 detik, untuk mengetahui pekerjaannya emang di interrupt
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable ke: ["+i+"]");
                try {
                    Thread.sleep(1_000L);
                } catch (InterruptedException e) {
                    // kalau thread ini menangkap ada sinyal interrupt
                    // maka hentikan perualangan untuk pekerjaan saat ini
                    // kalau kita catch exception, dan hanya diprint errornya, maka tugasnya tidak akan dihentikan, dan ini percuma menggunakan interrupt
                    // e.printStackTrace();

                    // nah untuk menghentikan pekerjaannya maka kita beri return;
                    return;
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();

        // misal kita akan nunggu 5 detik,
        // artinya perulangan akan dilakukan sampai ke-5 atau pekerjaannya hanya dilakukan 5 kali
        Thread.sleep(5_000L);

        // setelah 5 detik, kita akan interrupt threadnya
        thread.interrupt();

        System.out.println("Pending... from thread: ["+Thread.currentThread().getName()+"]");
        thread.join();
        System.out.println("Program finish from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    void threadInterruptCorrect() throws InterruptedException {
        // Thread Interrupt yang benar adalah menggunakan interuppted()
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {

                // jika thread ini mendeteksi sebuah interrupt, maka kita hentikan pekerjaan di thread ini
                // tapi ini akan sangat cepat prosesnya, kalau hanya pekerjaan 10 kali, maka tidak keliatan interrupt nya
                if (Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable ke: ["+i+"]");
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000L);
        thread.interrupt();

        System.out.println("Pending... from thread: ["+Thread.currentThread().getName()+"]");
        thread.join();
        System.out.println("Program finish from thread: ["+Thread.currentThread().getName()+"]");
    }
}
