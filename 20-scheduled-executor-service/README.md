# Scheduled Executor Service

- ExecutorService memiliki sub child interface bernama ScheduledExecutorService
- Fitur tambahan di ScheduledExecutorService adalah, kita bisa melakukan `asynchronous task yang terjadwal`
- Hal ini sangat cocok untuk kasus `delayed job` (pekerjaan yang butuh ditangguhkan pengerjaannya) dan `periodic job`
- ScheduledExecutorService merupakan fitur yang bisa menggantikan low level penggunaan Timer

# ScheduledFuture

- Hampir semua method di ScheduledExecutorService mengembalikan data ScheduledFuture
- ScheduledFuture sebenarnya mirip dengan Future, yang membedakan, dia adalah turunan dari interface Delayed, yang memiliki method untuk mendapatkan informasi waktu delay

# Membuat ScheduledExecutorService

- Untuk membuat ScheduledExecutorService kita bisa menggunakan implementasi class ScheduledThreadPoolExecutor
- Atau jika ingin mudah, kita bisa gunakan class Executors, terdapat `method newSingleThreadScheduledExecutor()` dan `newScheduledThreadPool(poolSize)` untuk membuat ScheduledExecutorService