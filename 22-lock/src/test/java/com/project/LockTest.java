package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    String message;

    @Test
    void testLock() throws InterruptedException {

        var counter = new CounterLock();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

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

    @Test
    void testReadWriteLock() throws InterruptedException {

        var counter = new CounterReadWriteLock();

        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
            System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

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

    @Test
    void condition() throws InterruptedException {

        // bikin variable lock
        var lock = new ReentrantLock();

        // bikin condition dari lock
        var condition = lock.newCondition();

        // bikin Thread 1
        var thread1 = new Thread(() -> {
            try {
                // kita lock dulu
                lock.lock();
                // tunggu condition sampai di notify dari Thread 2
                // jika sudah mendapatkan signal, maka waitnya akan dilepas
                condition.await();
                System.out.println("Ini thread 1 : " + message);
                System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // bikin Thread 3, fungsinya sama dengan Thread 1
        var thread3 = new Thread(() -> {
            try {
                lock.lock();
                condition.await();
                System.out.println("Ini thread 3 : " + message);
                System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // bikin Thread 2
        var thread2 = new Thread(() -> {
            try {
                // lock dulu
                lock.lock();
                System.out.println("Waiting....");
                // nunggu 2 detik dulu, baru signal nya dikirim
                Thread.sleep(2000);

                // kita set message disini
                message = "Ini message dari thread 2";
                // lalu kita kita kirimkan signal ke thread lain
                // kalau data message nya sudah ada

                // untuk satu thread saja
                // condition.signal();

                // jika lebih satu threadm, maka gunakan signalAll()
                condition.signalAll();
                System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        // jalankan thread 1 dan thread 3 dulu, karena thread 1 dan thread 3 menunggu sinyal datri thread 2
        thread1.setName("Thread-1");
        thread1.start();

        thread3.setName("Thread-3");
        thread3.start();

        thread2.setName("Thread-2");
        thread2.start();

        // lalu join
        thread1.join();
        thread3.join();
        thread2.join();
    }
}
