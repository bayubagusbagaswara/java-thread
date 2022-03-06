# Phaser

- Merupakan fitur synchronizer yang mirip dengan CyclicBarrier dan CountDownLatch, namun lebih flexible
- Sebelumnya, untuk jumlah counter atau threadnya sudah ditentukan di awal
- Namun pada Phaser, bisa berubah dengan menggunakan method register() atau bulkRegister(int), dan untuk menurunkannya bisa menggunakan method arrive..() atau bisa menggunakan await...(int) untuk menunggu sampai jumlah yang register tertentu