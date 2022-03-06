# Atomic Package

- Package Atomic berisikan class-class yang mendukung `lock-free` dan `thread-safe programming` pada single variable
- Setiap object Atomic class akan mengelola data yang diakses dan diupdate menggunakan method yang telah disediakan
- Atomic class melakukan implementasi `Compare-and-Swap` untuk mendukung synchronization
- Dengan menggunakan Atomic, kita `tidak perlu lagi menggunakan synchronized` secara manual