# ThreadPool

- Threadpool merupakan class yang digunakan untuk `management thread`
- Dengan threadpool, kita tidak perlu membuat thread secara manual, karena semua sudah diatur oleh threadpool
- Selain itu threadpool bisa melakukan reusable thread yang sudah selesai bekerja
- Threadpool di Java direpresentasikan dalam class bernama `ThreadPoolExecutor`

# Pengaturan ThreadPool

Ada beberapa yang harus kita atur ketika membuat threadpool
- `core pool`, adalah minimal thread yang akan  dibuat ketika threadpool dibuat
- `max pool`, adalah maximal thread yang akan dibuat
- `keep alive time`, adalah berapa lama thread akan dihapus jika tidak bekerja
- `queue`, adalah antrian untuk menampung pekerjaan yang dikirim ke threadpool

# Eksekusi Runnable

- Untuk melakukan `eksekusi Runnable`, kita bisa menggunakan method `execute()` milik threadpool
- Secara otomatis `data runnable akan dikirim ke queue threadpool` untuk dieksekusi oleh thread yang terdapat di threadpool

# Menghentikan ThreadPool

- Jika kita sudah selesai menggunakan threadpool, dan tidak akan menggunakannya lagi, ada baiknya kita hentikan dan matikan ThreadPool nya
- Caranya kita bisa menggunakan method `shutdown()` untuk menghentikan threadpool. Jika ada pekerjaan yang belum dikerjakan, maka akan di ignore
- Atau gunakan `shutdownNow()` untuk menghentikan threadpool. Namun, pekerjaan yang belum dikerjakan akan dikembalikan
- Atau jika kita ingin menunggu sampai threadpool selesai, kita bisa gunakan awaitTermination()

# Rejected Handler

- Apa yang terjadi jika `queue penuh` dan `thread juga semua sedang bekerja`?
- Maka secara otomatis akan di handle oleh object `RejectedExecutionHandler`
- Secara default, implementasi rejected handler akan mengembalikan exception RejectedExecutionException ketika submit(Runnable) pada kondisi queue penuh dan thread sedang bekerja semua
- Jika kita ingin mengubahnya, kita bisa membuat RejectedExecutionHandler sendiri