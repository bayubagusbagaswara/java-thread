package com.project;

public class MainApplication {
    public static void main(String[] args) {

        // mengetahui thread utama yang dijalankan
        final String name = Thread.currentThread().getName();
        System.out.println("Thread name: " + name);
    }
}
