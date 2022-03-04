# Synchronization

- Problem Race Condition bisa diselesaikan dengan Synchronization
- Synchronization merupakan fitur dimana kita memaksa kode program `hanya boleh diakses dan dieksekusi oleh satu thread` saja
- Hal ini menyebabkan thread yang lain yang akan mengakses kode program tersebut harus menunggu thread yang lebih dahulu mengaksesnya
- sehingga proses Synchronization akan lebih lambat. Namun, proses Synchronization lebih aman karena tidak akan terjadi race condition

# Synchronized Method

- Di Java, terdapat dua jenis `synchronized method` dan `synchronized statement`
- Synchronized method merupakan synchronization yang paling mudah, karena kita hanya perlu menambah kata kunci synchronized pada method yang ingin kita set sebagai synchronization
- Dengan begitu, secara otomatis method tersebut hanya bisa diakses oleh `satu thread pada satu waktu`

# Intrinsic Lock

- Synchronized di Java sebenarnya bekerja menggunakan `lock`
- Ketika kita melakukan synchronized method, maka secara otomatis Java akan membuat `intrinsic lock` atau monitor lock
- Ketika synchronized method dipanggil oleh thread, maka thread akan mencoba mendapatkan intrinsic lock
- Setelah method selesai dijalankan (sukses ataupun error), maka thread akan mengembalikan intrinsic lock
- Semua itu terjadi secara otomatis di synchronized method

# Synchronized Statement

- Saat kita menggunakan synchronized method, secara otomatis seluruh isi method akan ter-synchronization
- Kadang, misal kita hanya ingin melakukan synchronized pada bagian kode tertentu saja
- Untuk melakukan hal tersebut, kita bisa menggunakan synchronized statement
- Namun ketika kita menggunakan synchronized statement, kita harus menentukan object intrinsic lock sendiri
