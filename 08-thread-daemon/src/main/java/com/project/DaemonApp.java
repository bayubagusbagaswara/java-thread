package com.project;

public class DaemonApp {
    public static void main(String[] args) {

        // buat tread dan object runnable untuk pekerjaan thread nya
        var thread = new Thread(() -> {
            try {
                // sleep 3 detik
                Thread.sleep(3_000L);
                System.out.println("Thread from: ["+Thread.currentThread().getName()+"], is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // defaultnya jika kita tidak mengaktifkan thread Daemon, maka akan menunggu thread lain selesai menjalankan tugasnya

        // kita setDaemon() menjadi true
        // sehingga thread nya tidak akan ditunggu sampai selesai menjalankan pekerjaannya
        // karena program utama java nya sudah selesai
        thread.setDaemon(true);

        // jalankan thread nya
        thread.start();

        // disini kita tidak melakukan join thread, artinya tidak akan menunggu thread lain selesai menjalankan pekerjaannya

        System.out.println("Program finish from thread: ["+Thread.currentThread().getName()+"]");

        // Hasilnya thread daemon tidak ditunggu jika main thread sudah selesai menjalankan program Java nya
    }
}
