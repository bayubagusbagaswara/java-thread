package com.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {

    @Test
    @DisplayName("Array Blocking Queue")
    void arrayBlockingQueue() throws InterruptedException {

        // buat antrian (queue), misalnya 5 antrian
        final var queue = new ArrayBlockingQueue<>(5);

        // bikin executor threadpool, dan jumlah thread nya 20
        final var executor = Executors.newFixedThreadPool(20);

        // tiap iterasi, kita akan melakukan submit data kedalam antrian nya
        // karena kita langsung melakukan eksekusi task sebanyak 10, sedangkan array queue nya hanya ada 5, maka yang dieksekusi duluan adalah 5 task
        // 5 task nya sisanya akan dieksekusi menunggu data di antriannya ada yang mengambil dari thread lain
        for (int i = 0; i < 10; i++) {
            int data = i;
            executor.execute(() -> {
                try {
                    // masukkan data ke dalam antrian
                    queue.put("Data : ["+data+"]");
                    System.out.println("Memasukkan data: ["+data+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        // kita bikin thread lain, yang bertugas untuk mengambil data dari antrian setiap 2 detik sekali
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    // ambil data dari array queue (antrian)
                    var value = queue.take();
                    System.out.println("Mengambil Data : ["+value+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // tunggu semua thread nya selesai menjalankan tugasnya maksimal 30 detik
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Linked Blocking Queue")
    void linkedBlockingQueue() throws InterruptedException {

        // kalau array ada kapitasnya, sedangkan linked tidak ada batasanya alias unlimited

        // buat queue kapasitas kosong, karena linked bisa menambah secara otomatis
        final var queue = new LinkedBlockingQueue<>();

        // bikin executor, misal thrednya berjumlah 20
        final var executor = Executors.newFixedThreadPool(20);

        // harusnya 10 threadpool bisa langsung memasukkan semua datanya, tidak harus ada yang menunggu dahulu
        // baru akhirnya akan diambili datanya tiap 2 detik
        // jadi semua data akan langsung dimasukkan ke antrian, setelah selesai di masukkan ke antrian, maka baru bisa diambil datanya
        // INGAT! kalau kita mengguakan linkedBlockingQueue bisa saja akan muncul error Out Of Memory/Memory stackoverflow
        // karena memorynya akan bertambah terus
        for (int i = 0; i < 10; i++) {
            int data = i;
            executor.execute(() -> {
                try {
                    queue.put("Data : ["+data+"]");
                    System.out.println("Memasukkan data: ["+data+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Thread lain untuk mengambil data dari antriannya
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("Mengambil Data : ["+value+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Priority Blocking Queue")
    void priorityBlockingQueue() throws InterruptedException {
        // Priority ini memberi cara pengambilan datanya secara berurutan
        // Pertama kita akan memasukkan data mulai 0-9 melalui Thread pertama
        // lalu Thread kedua akan mengambil mulai dari 9 sampai 0
        // karena kita menggunakan Comparator Reverse

        // buat queue
        final var queue = new PriorityBlockingQueue<Integer>(10, Comparator.reverseOrder());

        // bikin executor
        final var executor = Executors.newFixedThreadPool(20);

        // harusnya 10 threadpool bisa langsung memasukkan semua datanya, tidak harus ada yang menunggu dahulu
        // baru akhirnya akan diambili datanya tiap 2 detik
        for (int i = 0; i < 10; i++) {
            int data = i;
            executor.execute(() -> {
                queue.put(data);
                System.out.println("Memasukkan data: ["+data+"], from thread: ["+Thread.currentThread().getName()+"]");
            });
        }

        // Thread lain untuk mengambil data dari antrian
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("Mengambil Data: ["+value+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Delayed Queue")
    void delayedQueue() throws InterruptedException {

        // buat queue
        final var queue = new DelayQueue<ScheduledFuture<String>>();

        // bikin executor
        final var executor = Executors.newFixedThreadPool(20);

        // kita perlu memasukkannya kedalam queue
        final var executorScheduled = Executors.newScheduledThreadPool(10);

        // harusnya 10 threadpool bisa langsung memasukkan semua datanya, tidak harus ada yang menunggu dahulu
        // baru akhirnya akan diambili datanya tiap 2 detik
        for (int i = 1; i <= 10; i++) {
            final int data = i;
            // memasukkan data kedalam scheduled
            queue.put(executorScheduled.schedule(() -> {
                        return "Data: " + data + ", thread: " + Thread.currentThread().getName();
                    }, data, TimeUnit.SECONDS)
            );
        }

        // Thread lain, ambil data dari thread lain
        executor.execute(() -> {
            while (true) {
                try {
                    var value = queue.take();
                    System.out.println("Mengambil Data : ["+value.get()+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(20, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Synchronous Queue")
    void synchronousQueue() throws InterruptedException {
        // Harus menunggu ada thread lain yang mengambil data terlebih dahulu
        // baru bisa memasukkan datanya
        // jika belum ada thread yang menarik datanya, maka akan menunggu dulu (tidak bisa memasukkan datanya)

        // buat queue atau antrian
        final var queue = new SynchronousQueue<String>();

        // bikin executor untuk threadpool
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            int data = i;
            executor.execute(() -> {
                try {
                    queue.put("Data: ["+data+"], from thread: ["+Thread.currentThread().getName()+"]");
                    System.out.println("Memasukkan data: ["+data+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Thread lain untuk mengambil data dari antrian
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("Mengambil Data: ["+value+"], from thread: "+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Blocking Deque")
    void blockingDeque() throws InterruptedException {

        // ini bisa LIFO atau tumpukkan
        // artinya data yang pertama masuk, maka dia paling akhir diambil
        // dan data yang terakhir masuk, maka dia yang pertama diambil

        final var queue = new LinkedBlockingDeque<String>();

        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            try {
                // putLast, yakni menambahkan di paling akhir
                queue.putLast("Data : " + i);
                System.out.println("Memasukkan data: ["+i+"], from thread: ["+Thread.currentThread().getName()+"]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Thread lain untuk mengambil data
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    // ambilnya menggunakan takeLast, yakni mengambil data dari yang paling belakang
                    var value = queue.takeLast();
                    System.out.println("Mengambil Data : ["+value+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }

    @Test
    @DisplayName("Transfer Queue")
    void transferQueue() throws InterruptedException {

        final var queue = new LinkedTransferQueue<String>();

        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var data = i;
            executor.execute(()-> {
                try {
                    queue.transfer(String.valueOf(data));
                    System.out.println("Memasukkan Data: ["+data+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Thread lain, tiap 2 detik datanya akan diambil atau ditarik
        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    var value = queue.take();
                    System.out.println("Mengambil Data: ["+value+"], from thread: ["+Thread.currentThread().getName()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Program finished from thread: ["+Thread.currentThread().getName()+"]");
    }
}
