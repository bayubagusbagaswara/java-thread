# Synchronizer

- Pada package concurrent, terdapat banyak sekali class untuk melakukan synchronizer, ini sebenarnya improvement dari locks, namun digunakan pada kasus-kasus tertentu
- Isi dari class-class Synchronizer banyak menggunakan locks, namun kita tidak perlu melakukannya secara manual, karena sudah diatur secara otomatis oleh class-class nya sendiri

# Jenis Synchronizer

Ada banyak sekali class-class yang bisa kita gunakan untuk synchronizer seperti:

1. Semaphore 
2. CountDownLatch 
3. CyclicBarrier 
4. Phaser, dan 
5. Exchanger

# Semaphore

- Class yang digunakan untuk manage data counter
- Nilai counter bisa naik, namun ada batas maksimalnya. Jika batas maksimalnya sudah tercapai, maka semua thread yang akan mencoba menaikkan counter harus menunggu, sampai ada thread lain yang menurunkan nilai counter tersebut
- Semaphore cocok sekali untuk menjaga agar thread berjalan pada maksimal total counter yang sudah kita tentukan

# CountDownLatch

- Merupakan synchronizer yang digunakan untuk menunggu beberapa proses selesai
- Cara kerjanya mirip dengan Semaphore, yang membedakannya adalah pada CountDownLatch, counter diawal sudah ditentukan
- Setelah proses selesai, kita akan menurunkan counter
- Jika counter sudah bernilai 0, maka yang melakukan wait bisa lanjut berjalan
- CountDownLatch cocok jika kita ingin menunggu beberapa proses yang berjalan secara asynchronous sampai semua proses selesai

# CyclicBarrier

- CyclicBarrier merupakan fitur yang bisa kita gunakan untuk saling menunggu, sampai jumlah thread yang menunggu terpenuhi
- Diawal kita akan tentukan berapa jumlah thread yang menunggu, jika sudah terpenuhi, maka secara otomatis proses menunggu akan selesai

# Phaser

- Merupakan fitur synchronizer yang mirip dengan CyclicBarrier dan CountDownLatch, namun lebih flexible
- Sebelumnya, untuk jumlah counter atau threadnya sudah ditentukan di awal
- Namun pada Phaser, bisa berubah dengan menggunakan method register() atau bulkRegister(int), dan untuk menurunkkannya bisa menggunakan method arrive..() atau bisa menggunakan await...(int) untuk menunggu sampai jumlah yang register tertentu

# Exchanger

- Exchanger merupakan fitur synchronizer untuk melakukan pertukaran data antar thread
- Jika data belum tersedia, maka thread yang melakukan pertukaran data akan menunggu sampai ada thread lain yang melakukan pertukaran data

