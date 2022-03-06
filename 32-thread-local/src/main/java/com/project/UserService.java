package com.project;

public class UserService {

    // kita bikin thread local
    final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    // pertama kita akan bikin usernya
    // kedua kita bisa akses methodnya

    // method setter
    public void setUser(String user) {
        // masukkan user ke threadLocal
        threadLocal.set(user);
    }

    // untuk mengetahui siapa user saat ini yang sedang mengakses
    public void doAction(Integer index) {
        var user = threadLocal.get();
        System.out.println(user + " diakses oleh " + index);
    }
}
