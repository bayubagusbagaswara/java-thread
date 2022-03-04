# Thread Communication

- Dalam multithreading programming, kadang sudah biasa ketika sebuah thread perlu menungg thread lain menyelesaikan tugas tertentu, baru thread tersebut melakukan tugasnya
- Tidak ada cara otomatis komunikasi antar thread secara langsung
- Tujuannya adalah agar sebuah thread selesai melakukan tugasnya, maka dia bisa beritahu ke thread yang lain
- Biasanya ini digunakan untuk sharing variable antar thread
- Oleh karena itu, programmer harus melakukannya secara manual untuk komunikasi antar thread

# Wait dan Notify

- Menggunakan loop untuk menunggu, sangat tidak direkomendasikan
- Alasannya adalah buang-buang resource CPU dan juga jika terjadi interrupt, loop akan terus berjalan tanpa henti
- Java sudah menyediakan solusi yang lebih baik dengan menambahkan method `wait dan notify` di java.lang.Object
- Artinya kita bisa membuat object apapun menjadi lock, dan gunakan wait() untuk menunggu, dan gunakan notify() untuk memberitahu bahwa sudah tersedia
- `notify()` akan memberi tahu thread lain yang sedang melakukan wait() bahwa proses bisa dilanjutkan

# Notify All

- Kadang ada kasus dimana sebuah lock ditunggu oleh banyak thread, notify() hanya memberi sinyal kepada satu thread saja
- Jika kita ingin mengirim sinyal ke semua thread, kita bisa menggunakan method `notifyAll()`