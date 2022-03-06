package com.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinTest {

    @Test
    void createPool() {

        // parallelism sesuai jumlah processor kita
        var forkJoinPool1 = ForkJoinPool.commonPool();
        var forkJoinPool2 = new ForkJoinPool(4);

        // atau bisa langsung executor
        var executor1 = Executors.newWorkStealingPool();
        var executor2 = Executors.newWorkStealingPool(4);

        // gimana kalau kita membuat pekerjaannya?
        // agak sedikit komplek membuat pekerjaan untuk forkJoinPool
    }

    @Test
    @DisplayName("Recursive Action Test")
    void recursiveAction() throws InterruptedException {
        // bisa batasin parallelism nya
        var pool = new ForkJoinPool(5);
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        // buat task
        var task = new SimpleForkJoinTask(integers);

        // execute tasknya didalam pool
        pool.execute(task);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }


    @Test
    @DisplayName("Recursive Task Test")
    void recursiveTask() throws InterruptedException, ExecutionException {

        var pool = new ForkJoinPool(5);
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        var task = new TotalRecursiveTask(integers);
        ForkJoinTask<Long> submit = pool.submit(task);

        Long aLong = submit.get();
        System.out.println(aLong);

        long sum = integers.stream()
                .mapToLong(value -> {
                    return value;
                })
                .sum()
                ;

        System.out.println(sum);

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.DAYS);
    }

    // buat method SimpleForkJoinTask
    public static class SimpleForkJoinTask extends RecursiveAction {
        // tambahkan List datanya
        private List<Integer> integers;

        // constructor
        public SimpleForkJoinTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected void compute() {
            // kita cek dulu size data listnya
            // misal jika memenuhi kondisi, maka tasknya akan dieksekusi
            // jika ukuran list datanya masih besar, maka akan kita lakukan fork (memecah tasknya)
            if (integers.size() <= 10 ) {
                // eksekusi task
                doExecute();
            } else {
                // fork
                forkCompute();
            }
        }

        private void forkCompute() {
            // kita bagi dua dulu list tasknya
            List<Integer> integers1 = this.integers.subList(0, this
                    .integers.size() / 2);
            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this
                    .integers.size());
            // lalu buat lagi SimpleForkJoinTask nya
            SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
            SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);

            ForkJoinTask.invokeAll(task1, task2);
        }

        private void doExecute() {
            // kita eksekusi tasknya
            integers.forEach(integer -> System.out.println(Thread.currentThread().getName() + " : " + integer));
        }
    }

    // buat RecursiveTask
    public static class TotalRecursiveTask extends RecursiveTask<Long> {

        private List<Integer> integers;

        public TotalRecursiveTask(List<Integer> integers) {
            this.integers = integers;
        }

        @Override
        protected Long compute() {
            if (integers.size() <= 10) {
                return doCompute();
            } else {
                return forkCompute();
            }
        }

        private Long forkCompute() {
            // setelah di split, buat recursive task
            List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
            List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());

            TotalRecursiveTask task1 = new TotalRecursiveTask(integers1);
            TotalRecursiveTask task2 = new TotalRecursiveTask(integers2);

            // lakukan fork join task invoke all
            ForkJoinTask.invokeAll(task1, task2);

            return task1.join() + task2.join();
        }

        private Long doCompute() {
            // kalkulasi semua tasknya menggunakan SUM
            return integers.stream()
                    .mapToLong(value -> {
                        return value;
                    })
                    .peek(value -> {
                        System.out.println(Thread.currentThread().getName() + " : " + value);
                    })
                    .sum()
                    ;
        }
    }
}
