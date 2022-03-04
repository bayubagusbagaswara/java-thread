package com.project;

public class SynchronizedStatementCounter {

    private Long value = 0L;

    // kita ingin synchronized kode program yang ingin di intrinsic lock
    // jadi tidak satu method di lock, hanya kode program yang diingikan saja
    // misal kita hanya me-lock pada perintah increment value++
    public void increment() {

        synchronized (this) {
            // hanya bisa diakses single thread atau satu per satu
            value++;
        }
        // kode program disini (selain value++) sudah tidak synchronized, artinya bisa diakses multiple thread
    }

    // method untuk mengambil data value
    public Long getValue() {
        return value;
    }
}
