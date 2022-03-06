package com.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {

    // kita buat executor untuk threadpool nya
    // dan kita buat jumlah thread di threadpoolnya adalah 10
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    // kita buat object Random
    private final Random random = new Random();

    // kita bikin method getValue
    public CompletableFuture<String> getValue() {

        // create future
        CompletableFuture<String> future = new CompletableFuture<>();

        // complete untuk memberitahu bahwa future nya sudah selesai
        // jika tidak ada, maka future akan berputar terus, karena belum dapat datanya
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                // masukkan data ke future
                future.complete("Bayu Bagus Bagaswara");
                System.out.println("Thread method getValue: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    @Test
    @DisplayName("Manual Create CompletableFuture")
    void create() throws ExecutionException, InterruptedException {
        // dapatkan data future dari method getValue()
        Future<String> future = getValue();
        System.out.println("Data future: ["+future.get()+"]");
        System.out.println("Thread finish: ["+Thread.currentThread().getName()+"]");
    }

    // kita bikin method execute
    // misalnya melakukan sesuatu dalam waktu beberapa lama
    // setelah selesai, lalu kita masukan value ke future nya
    private void execute(CompletableFuture<String> future, String value) {

        executor.execute(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(5000));
                // masukkan data ke future dengan complete
                future.complete(value);
                System.out.println("Thread method execute: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }

    // bikin method fastTest
    public Future<String> getFastest() {

        CompletableFuture<String> future = new CompletableFuture<>();
        // kirim future nya ke method execute
        // siapa yang paling cepat diantara 3 thread ini
        // dia yang akan pertama memasukkan valuenya kedalam future nya
        execute(future, "Data di Thread 1");
        execute(future, "Data di Thread 2");
        execute(future, "Data di Thread 3");
        return future;
    }

    @Test
    void testFastest() throws ExecutionException, InterruptedException {
        // ambil data yang paling cepat di set di CompletableFuture nya
        System.out.println("Data yang paling cepat: ["+getFastest().get()+"]");
    }

    @Test
    void completionStage() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = getValue();

        // kita bisa langsung melakukan operasi,
        // tanpa harus ada data future nya dulu
        // Ingat disini kita belum memasukkan data ke future
        // karena kita baru mendapatkan data future dari method getValue()
        CompletableFuture<String[]> future2 = future
                // misal kita ubah ke uppercase
                .thenApply(String::toUpperCase)
                // pisahkan menjadi array of string
                .thenApply(string -> string.split(" "));

        // data dari future method getValue adalah Bayu Bagus Bagaswara
        // setelah diolah akan menjadi BAYU BAGUS BAGASWARA
        String[] strings = future2.get();
        for (var value: strings) {
            System.out.println("Data: ["+value+"]");
        }

        System.out.println("Thread finish: ["+Thread.currentThread().getName()+"]");
    }
}
