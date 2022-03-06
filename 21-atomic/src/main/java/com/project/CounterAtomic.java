package com.project;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {

    // data aslinya ada didalam Atomicnya
    // jadi bisa menjaga dari problem race condition
    // jadi tipe data nya adalah Atomic
    // ada banyak class Atomic, seperti AtomicBoolean, AtomicInteger, AtomicReference
    private AtomicLong value = new AtomicLong(0L);

    public void increment() {
        // dengan menggunakan Atomic kita akan mendapatkan method incrementAndGet()
        // dan dengan method ini mencegah terjadi nya race condition
        value.incrementAndGet();
    }

    public Long getValue() {
        return value.get();
    }
}
