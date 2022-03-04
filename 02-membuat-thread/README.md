# Membuat Thread

- Membuat Thread bukan berarti kita melakukan pekerjaan
- Jadi kita perlu membuat sebuah pekerjaan terlebih dahulu yang akan dieksekusi oleh Thread nya
- Untuk membuat pekerjaan, kita perlu membuat object dari `interface Runnable`
- Selanjutnya object Runnable tersebut bisa kita masukan ke dalam Thread untuk dijalankan oleh Thread tersebut
- Saat Thread menjalankan Runnable nya, maka dia akan berjalan secara `asynchronous`, artinya dia akan berjalan sendiri, dan kode program kita akan berlanjut ke kode program selanjutnya
- Untuk menjalankan Thread, kita bisa memanggil function `start()` milik Thread