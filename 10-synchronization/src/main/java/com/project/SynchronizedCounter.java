package com.project;

public class SynchronizedCounter {

    private Long value = 0L;

    // Synchronized method cukup tambahkan keyword synchronized
    // artinya setiap akses terhadap method ini, maka hanya bisa diakses oleh satu thread pada satu waktu, thread yang lain akan menunggu
    // konsekuensinya adalah menjadi lambat, karena beberapa thread harus antri
    public synchronized void increment() {
        value++;
    }

    // method untuk mengambil data value
    public Long getValue() {
        return value;
    }
}
