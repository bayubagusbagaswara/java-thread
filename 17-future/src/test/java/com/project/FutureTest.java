package com.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    @DisplayName("Callable dan Future")
    void testFuture() throws ExecutionException, InterruptedException {

        // kita bikin executor service untuk single thread atau hanya menjalankan 1 thread di di threadpool
        var executor = Executors.newSingleThreadExecutor();

        // Callable akan mengembalikan data, setelah thread selesai menjalankan tasknya
        // misalnya setelah 5 detik baru ada ada datanya
        // anggap saja Callable ini adalah runnable atau pekerjaan untuk thread
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            System.out.println("Running task from thread: ["+Thread.currentThread().getName()+"]");
            return "Hi";
        };

        // selanjutnya kita submit callable nya, artinya kita memasukkan task ke antrian di threadpool executor
        // dan secara otomatis executor akan menjalankan thread, lalu mengsekusi task dari antrian nya
        // method submit ini dan mengembalikan nilai, dan dibungkus oleh Future
        Future<String> future = executor.submit(callable);
        System.out.println("Selesai future");

        // cek apakah thread nya selesai mengembalikan data future nya atau belum
        while(!future.isDone()) {
            // jika belum done future nya, maka kita tunggu
            // misal kita tunggu tiap 1 detik
            // karena task kita baru mengembalikan data setelah 5 detik
            System.out.println("Waiting data future...");
            Thread.sleep(1000);
        }

        // kalau ingin mengambil data dari future, maka gunakan get
        String value = future.get();
        System.out.println("Data from future: ["+value+"]");
        System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Future Cancel")
    void testFutureCancel() throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            System.out.println("Running task from thread: ["+Thread.currentThread().getName()+"]");
            return "Hi";
        };

        Future<String> future = executor.submit(callable);
        System.out.println("Selesai future");

        Thread.sleep(2000);
        // misal setelah 2 detik, maka kita cancel future nya
        // interrupt nya kita set menjadi true
        future.cancel(true);

        // harusnya sudah dicancel, karena dia baru selesai setelah 5 detik
        String value = future.get();
        System.out.println("Data from future: ["+value+"]");
        System.out.println("Thread from: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Invoke All")
    void invokeAll() throws InterruptedException, ExecutionException {

        // kita buat threadpool yang berisi 10 thread
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // kita buat Callable dalam List
        // misalnya kita punya task berjumlah 10, yang nantinya kita masukkan antrian ke threadpool executor
        List<Callable<String>> callables = IntStream.range(1, 11)
                .mapToObj(value -> (Callable<String>) () -> {
                    Thread.sleep(value * 500L);
                    return "Value: ["+value+"], from thread: ["+Thread.currentThread().getName()+"]";
                })
                .collect(Collectors.toList())
                ;

        // biasanya kan kita submit satu persatu
        // karena kita punya callable yang banyak, kita gunakan invokeAll
        // balikannya berupa Future yang digabungkan ke dalam satu list atau List of Future
        List<Future<String>> futures = executor.invokeAll(callables);

        // kalau mau ambil datanya satu persatu, maka kita bisa iterasi foreach
        for (Future<String> stringFuture : futures) {
            System.out.println("Data future: ["+stringFuture.get()+"]");
        }
        System.out.println("Thread finish from: ["+Thread.currentThread().getName()+"]");

        // Jadi, meskipun sleep pada callable berbeda-beda, tapi invokeAll akan menunggu semua selesai task
        // setelah task semuanya selesai, baru kita ambil data future nya
    }

    @Test
    @DisplayName("Invoke Any")
    void invokeAny() throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        List<Callable<String>> callables = IntStream.range(1, 11)
                .mapToObj(value -> new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Thread.sleep(500);
                        return "Value: ["+value+"], from thread: ["+Thread.currentThread().getName()+"]";
                    }
                })
                .collect(Collectors.toList())
                ;

        // lakukan invokeAny
        String value = executor.invokeAny(callables);

        // ini akan mengambil data yang paling cepet selesai dari thread nya
        // ini hanya 1 data, karena hanya mengambil data yang paling cepat
        System.out.println("Data: ["+value+"]");
        System.out.println("Thread finish from: ["+Thread.currentThread().getName()+"]");

        // dan Value yang didapatkan adalah Value 1
        // karena value 1 kita melakukan sleep hanya 500 milisecond
        // atau kita ubah sleep untuk callablenya, pasti nanti datanya akan diambil secara acak (tergantung thread mana dulu yang selesai menjalankan tasknya)
    }
}
