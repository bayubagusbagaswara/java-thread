package com.project;

import org.junit.jupiter.api.Test;

public class DeadlockTest {

    @Test
    void transfer() throws InterruptedException {

        // Contoh kasus kita adalah method transfer akan diakses oleh beberapa Thread secara bersamaan
        // Nah untuk itu kita pastikan antar Thread tidak meneyebabkan Deadlock
        // Thread 1 melakukan transfer dari A ke B
        // Thread 2 melakukan transfer dari B ke A
        // hal itu dilakukan secara bersamaan
        // caranya dengan synchronized di method transfer nya
        // teknisnya meskipun terlihat bersama, tapi untuk akses data hanya bisa diakses satu Thread yang bergantian

        // misal saldo A ada 1 juta
        var balanceA = new Balance(1_000_000L);
        // misal saldo B ada 1 juta
        var balanceB = new Balance(1_000_000L);

        // bikin thread1
        // misal A transfer ke B sejumlah 500.000
        // sehingga balanceA = 500.000 dan balanceB = 1.500.000
        var thread1 = new Thread(() -> {
            try {
                Balance.transfer(balanceA, balanceB, 500_000L);
                System.out.println("Transfer balance1 to balance2 from thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // bikin thread2
        // misal B transfer ke A sejumlah 300.000
        // dari thread1, balanceA = 500.000 dan balanceB = 1.500.000
        // seharusnya hasil thread2 adalah balanceA = 800.000 dan balanceB = 1.200.000
        var thread2 = new Thread(() -> {
            try {
                Balance.transfer(balanceB, balanceA, 300_000L);
                System.out.println("Transfer balance2 to balance1 from thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // kita jalankan semua thread1 dan thread2
        thread1.start();
        thread2.start();

        // kita join untuk menunggu semua threadnya selesai
        thread1.join();
        thread2.join();

        // hasilnya adalah seperti keadaan awal
        // dan harusnya 2 detik selesai,
        // tapi yang terjadi adalah DIAM (DEADLOCK)
        System.out.println("Balance 1: ["+balanceA.getValue()+"]");
        System.out.println("Balance 2: ["+balanceB.getValue()+"]");
        System.out.println("Program finis from thread: ["+Thread.currentThread().getName()+"]");
    }
}
