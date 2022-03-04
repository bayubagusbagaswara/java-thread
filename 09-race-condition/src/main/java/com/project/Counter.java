package com.project;

public class Counter {

    private Long value = 0L;

    // method untuk increment (mengubah) data value
    public void increment() {
        value++;
    }

    // method untuk mengambil data value
    public Long getValue() {
        return value;
    }
}
