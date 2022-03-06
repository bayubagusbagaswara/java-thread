package com.project;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    @Test
    void testThreadLocal() throws InterruptedException {

        final var random = new Random();
        final var userService = new UserService();
        final var executor = Executors.newFixedThreadPool(10);

        // perulangan untuk menjalankan 50 task
        for (int i = 0; i < 50; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    // set user dulu
                    userService.setUser("User ke- " + index);
                    Thread.sleep(1000 + random.nextInt(3000));
                    userService.doAction(index);
                    System.out.println("Menambahkan user from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }
}