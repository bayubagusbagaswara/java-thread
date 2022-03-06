package com.project;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

    private Random random = new Random();

    @Test
    void testCompletionService() throws InterruptedException {

        // kita buat thread sebanyak 10 di thread pool nya,
        var executor = Executors.newFixedThreadPool(10);

        // buat completionService yang membutuhkan executor juga
        var completionService = new ExecutorCompletionService<String>(executor);

        // BAGIAN SUBMIT TASL ATAU YANG MENGEKSEKUSI TASK
        // kita coba submit task, misal kita submit 100 task
        // kita buat dulu executor baru untuk merunning task nya
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 100; i++) {
                final var index = i;
                // masukkan task kedalam completionService
                completionService.submit(() -> {
                    Thread.sleep(random.nextInt(2000));
                    return "Task: ["+index+"]";
                });
            }
        });

        // BAGIAN POLL TASK ATAU YANG MENERIMA HASIL TASK
        // kita gunakan poll untuk ambil data dari completionService
        // kita pake perulangan while true, ambil jika datanya ada
        // jika selama 5 detik tidak ada datanya, maka balikannya adalah null
        // jika ada datanya langsung saja ambil dengan future.get()
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    Future<String> future = completionService.poll(5, TimeUnit.SECONDS);
                    // cek ada atau tidak datanya
                    if (future == null) {
                        break;
                    } else {
                        // ambil data dari future
                        System.out.println("Future get: ["+future.get()+"]");
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        // TUNGGU SEMUA THREAD MENJALANKAN TUGASNYA
        // executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
    }
}