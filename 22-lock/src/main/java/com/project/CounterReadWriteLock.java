package com.project;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {

    private Long value = 0L;

    // data bertipe ReadWriteLock
    final private ReadWriteLock lock = new ReentrantReadWriteLock();

    // kita lock juga bagian ini
    public void increment() {
        try {
            lock.writeLock().lock();
            value++;
        } finally {
            lock.writeLock().unlock();
        }
    }

    // kita bedakan lock untuk getValue juga
    // jadi hanya ada satu thread aja yang bisa mengakses bagian ini
    public Long getValue() {
        try {
            lock.readLock().lock();
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }
}
