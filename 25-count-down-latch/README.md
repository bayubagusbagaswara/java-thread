# CountDownLatch

- Merupakan synchronizer yang digunakan untuk menunggu beberapa proses selesai
- Cara kerjanya mirip dengan Semaphore, yang membedakannya adalah pada CountDownLatch, counter diawal sudah ditentukan
- Setelah proses selesai, kita akan menurunkan counter
- Jika counter sudah bernilai 0, maka yang melakukan wait bisa lanjut berjalan
- CountDownLatch cocok jika kita ingin menunggu beberapa proses yang berjalan secara asynchronous sampai semua proses selesai