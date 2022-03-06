package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    @Test
    void testExchanger() throws InterruptedException {

        // create exchaner
        final var exchanger = new Exchanger<String>();

        // create threadpool dengan jumlah thread 10
        final var executor = Executors.newFixedThreadPool(10);

        // Thread yang Pertama adalah thread yang akan mengirim data First
        executor.execute(() -> {
            try {
                System.out.println("Thread 1 : Send : First");
                Thread.sleep(1000);
                // kita simpan datanya di result
                var result = exchanger.exchange("First");
                System.out.println("Thread 1 : Receive : ["+result+"], from Thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread yang Kedua, yang akan mengirim data Second
        executor.execute(() -> {
            try {
                System.out.println("Thread 2 : Send : Second");
                Thread.sleep(2000);
                var result = exchanger.exchange("Second");
                System.out.println("Thread 2 : Receive : ["+result+"], from Thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(20, TimeUnit.SECONDS);
    }
}
