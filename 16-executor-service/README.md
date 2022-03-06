# ExecutorService

- ThreadPoolExecutor merupakan implementasi dari interface Executor dan ExecutorService
- Dan sebenarnya pembuatan Threadpool secara manual itu jarang dilakukan, kecuali pada kasus yang benar-benar butuh melakukan tunning Threadpool
- Rata-rata untuk mengeksekusi Runnable, biasanya kita akan menggunakan ExecutorService, tidak menggunakan threadpool manual

# Executors

- Karena ExecutorService adalah interface, maka pembuatan object ExecutorService salah satunya adalah dengan menggunakan ThreadPoolExecutor
- Namun, ada cara yang lebih mudah, yaitu dengan menggunakan class Executors
- Executors merupakan class utility untuk membantu kita membuat object ExecutorService secara mudah
- Sebenarnya implementasi Executors pun menggunakan ThreadPoolExecutor, hanya saja kita tidak perlu terlalu pusing melakukan pengaturan threadpool secara manual

# Executors Method 

- `newFixedThreadPool(n)` berfungi untuk membuat threadpool dengan jumlah pool min dan max fix
- `newSingleThreadExecutor()` berfungsi untuk membuat threadpool dengan jumlah pool min dan max 1
- `newCacheThreadPool()` berfungsi untuk membuat threadpool dengan jumlah thread bisa bertambah tidak terhingga

# Queue Executors

- Hati-hati ketika membuat ExecutorService menggunakan Executors class
- Karena rata-rata Threadpool yang dibuat memiliki kapasitas queue (antrian) tidak terbatas
- Artinya jika terlalu banyak Runnable task didalam queue, maka memori penyimpanan yang akan terpakai akan semakin besar