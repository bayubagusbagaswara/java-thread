package com.project;

public class Balance {

    private Long value;

    public Balance(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    // method untuk transfer yang Deadlock
    // parameter "from" nya akan kita lock (biar tidak ada orang yang menggunakan parameter from juga)
    // lalu parameter "to" kita lock juga
    public static void transferDeadlock(Balance from, Balance to, Long value) throws InterruptedException {

        // lock untuk data "balance from"
        synchronized (from) {
            // misal kita sleep 1 detik untuk from ini
            Thread.sleep(1000L);

            // lalu misalnya kita lock juga untuk data "balance to"
            synchronized (to) {
                // sleep 1 detik
                // baru kita lakukan proses transfernya
                Thread.sleep(1000);
                from.setValue(from.getValue() - value);
                to.setValue(to.getValue() + value);
            }
        }
    }
    // Dealock terjadi karena antar thread menunggu data balance yang di lock

    // kita bedakan block antar synchronized nya
    public static void transfer(Balance from, Balance to, Long value) throws InterruptedException {

        // setelah kita lock "from" nya, maka kita unlock parameter "from"
        // lalu bisa melanjutkan lock lagi ke parameter to
        // jadi kita lakukan satu per satu untuk lock nya

        synchronized (from) {
            Thread.sleep(1000);
            from.setValue(from.getValue() - value);
        }

        synchronized (to) {
            Thread.sleep(1000);
            to.setValue(to.getValue() + value);
        }
    }
}
