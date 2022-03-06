package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    void parallel() {

        Stream<Integer> stream = IntStream.range(0, 1000).boxed();

        // stream ini akan di running di ForkJoinPool
        // jadi bukan hanya dirunning di thread main, tapi juga thread ForkJoinPool (yang jumlahnya sesuai dengan CPU kita)
        stream
                .parallel()
                .forEach(integer -> {
                    System.out.println(Thread.currentThread().getName() + " : " + integer);
                });
    }

    @Test
    void customPool() {

        // bikin poolnya
//        var pool = ForkJoinPool.commonPool();
         var pool = new ForkJoinPool(1);

        // lalu submit (ada data balikannya)
        ForkJoinTask<?> task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(0, 1000).boxed();
            stream
                    .parallel()
                    .forEach(integer -> {
                        System.out.println(Thread.currentThread().getName() + " : " + integer);
                    });
        });

        task.join();
    }
}
