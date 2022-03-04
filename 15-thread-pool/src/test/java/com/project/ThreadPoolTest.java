package com.project;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    @Test
    void createThreadPool() {

        // minimal thread nya
        var minThread = 10;

        // maksimal thread nya
        var maxThread = 100;

        // jumlah waktu penghapusan thread
        // jika threadnya sudah tidak digunakan lagi
        var alive = 1;

        // satuan waktunya
        var aliveTime = TimeUnit.MINUTES;

        // kita bikin object untuk antrian task/pekerjaan yang nantinya akan dipanggil oleh thread
        // antrian ini menampung pekerjaan yang dikirim ke threadpool
        // jadi object queue ini nantinyan berisi semua pekerjaan, dimana pekerjaan itu akan dijalankan oleh thread
        var queue = new ArrayBlockingQueue<Runnable>(100);

        // kita bikin object untuk ThreadPoolnya
        // disini kita masukkan nilai-nilai untuk pengaturan ThreadPool
        // didalam ThreadPool ini berisi banyak Thread
        // didalam ThreadPool juga kita masukkan antrian pekerjaan yang akan dijalankan oleh Thread nya
        var threadPool = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);
    }

    @Test
    void executeRunnable() throws InterruptedException {

        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;

        // buat object untuk antrian pekerjaan
        var queue = new ArrayBlockingQueue<Runnable>(100);

        // ThreadPool
        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        // Selanjutnya kita eksekusi ThreadPool nya dengan menggunakan method execute()
        // dengan parameternya adalah runnable

        // kita buat object runnable untuk pekerjaan yang akan dijalankan
        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
                System.out.println("Runnable from thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // masukkan pekerjaan ke dalam threadpool, lalu eksekusi threadpoolnya
        // pekerjaan runnable ini akan dimasukan kedalam object queue/antrian didalam threadpool
        executor.execute(runnable);

        System.out.println("Thread : ["+Thread.currentThread().getName()+"]");

        Thread.sleep(6000);

        System.out.println("Semua thread selesai");
    }

    @Test
    void shutdown() throws InterruptedException {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(1000);
        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        // misalnya kita akan buat task sebanyak 1000
        // sedangkan kita hanya punya minThread 10 dan maxThread 100
        // jadi akan bergantian tiap 10, karena core thread nya adalah 10
        for (int i = 0; i < 1000; i++) {

            final var task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task ["+task+"] from Threadpool : ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            // kita jalankan threadpoolnya
            executor.execute(runnable);
        }

        // kita menghentikan Threadpool nya
        // jadi ini belum sampai mengeksekusi runnable diatas, tapi sudah mati
        executor.shutdown();

        // jika kita ingin menunggu task yang terlanjur dieksekui threadnya, maka gunakan waitTermination()
        // harapannya 1 hari tasknya selesai
        // executor.awaitTermination(1, TimeUnit.DAYS);
    }

    // untuk menghandle jika ternyata kelebihan task/pekerjaan
    // misal thread nya sibuk dan antrian nya juga sudah penuh, lalu apa yang ingin dilakukan
    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
            // misal kita hanya ingin log task nya
            // ini task yang tidak dijalankan, atau tidak dimasukkan ke thread pool
            System.out.println("Task ["+runnable+"] is rejected");
        }
    }

    @Test
    void rejected() throws InterruptedException {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;

        // misal antrian tasknya hanya 10
        var queue = new ArrayBlockingQueue<Runnable>(10);

        // buat object rejected
        var rejectedHandler = new LogRejectedExecutionHandler();

        // masukkan rejectedHandler ke parameter
        // jadi saat treadpool ini sibuk, maka bisa me-reject task/pekerjaannya
        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue, rejectedHandler);

        // kita buat perulangan sebanyak 200
        // jadi tasknya nanti ada 200
        // karena kita antriannya hanya 10, dan max threadnya 100
        // maka hanya sekitar 110 task yang bisa dihandle oleh threadpool ini
        // sisanya akan direject
        for (int i = 0; i < 200; i++) {
            final var task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(100);
                    System.out.println("Task ["+task+"] from Threadpool : ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }

        // harapan dari kita, bahwa 1 menit itu bisa menyelesaikan semua task
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}