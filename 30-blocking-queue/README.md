# Blocking Queue

- BlockingQueue merupakan turunan dari Queue, dari namanya kita tahu bahwa ini adalah Collection dengan tipe antrian
- Yang membedakan sifat dari BlockingQueue adalah dia aman diakses oleh beberapa thread, baik itu yang memasukkan data atau mengambil data
- BlockingQueue mendukung method `wait` ketika mengambil data, dan juga wait ketika menyimpan data
- Jika queue kosong, thread yang mengambil data akan diminta untuk menunggu sampai ada data
- Dan jika queue penuh, thread yang mengambil data akan diminta untuk menunggu sampai ada tempat kosong

# Implementasi Blocking Queue

- ArrayBlockingQueue, implementasi BlockingQueue dengan ukuran fix
- LinkedBlockingQueue, implementasi BlockingQueue dengan ukuran bisa berkembang
- PriorityBlockingQueue, implementasi BlockingQueue dengan otomatis berurut berdasarkan prioritas
- DelayQueue, implementasi BlockingQueue untuk tipe data Delayed, dimana data tidak bisa diambil sebelum waktu delay yang telah ditentukan
- SynchronousQueue, implementasi BlockingQueue dimana thread yang menambah data harus menunggu sampai ada thread yang mengambil data, begitu juga kebalikannya

# BlockingDeque

- BlockingDeque merupakan turunan dari BlockingQueue
- BlockingDeque tidak hanya mendukung FIFO (First In First Out), tapi juga LIFO (Last In Last Out)
- Implementasi BlockingDeque hanyalah LinkedBlocking Deque

# TransferQueue

- TransferQueue merupakan turunan dari BlockingQueue yang memperbolehkan pengirim data ke queue menunggu sampai data ada yang menerima
- Implementasi TransferQueue hanyalah LinkedTransferQueue