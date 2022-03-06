package com.project;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {

    @Test
    void concurrentMap() throws InterruptedException {

        final var countDown = new CountDownLatch(100);

        // bikin map
        final var map = new ConcurrentHashMap<Integer, String>();

        // bikin executor threadpool, misal thread nya ada 100
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    // masukkan data jika ada, jika tidak ada maka tidak dimasukkan
                    map.putIfAbsent(index, "Data-" + index);
                    System.out.println("Memasukkan data from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // setelah memasukkan data ke map, lalu kita lakukan countdown
                    countDown.countDown();
                }
            });
        }

        // kita bikin thread lagi untuk countdown await
        executor.execute(() -> {
            try {
                countDown.await();
                map.forEach((integer, s) -> {
                    System.out.println(integer + " : " + s + ", from thread: " + Thread.currentThread().getName());
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    void testCollection() {

        // misal kita punya list
        List<String> list = new ArrayList<>();
        list.add("Albert");
        list.add("Newton");
        list.add("Tesla");
        list.add("Faraday");

        // kita ingin list ini bisa diakses oleh multiple thread, maka konversi menjadi synchronized
        List<String> stringList = Collections.synchronizedList(list);
    }
}
