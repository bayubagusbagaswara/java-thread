package com.project;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {

    private Long value = 0L;

    final private Lock lock = new ReentrantLock();

    public void increment() {
        try {
            // pastikan kita menjalankan lock dulu
            // setelah itu baru jalankan increment
            lock.lock();
            value++;
        } finally {
            // di finally kita unlock
            // kenapa harus di finally? karena jika try terjadi exception, maka lock nya tetap di unlock
            lock.unlock();
        }
    }

    public Long getValue() {
        return value;
    }
}
