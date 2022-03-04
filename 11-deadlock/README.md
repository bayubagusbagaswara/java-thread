# Deadlock

- Jika kita salah melakukan synchronization maka akan timbul masalah lagi, yakni `Deadlock`
- Deadlock merupakan kondisi dimana beberapa thread saling menunggu satu sama lain
- Biasanya terjadi ketika thread tersebut melakukan lock dan menunggu lock lain dari thread lain. Dan ternyata thread tersebut juga menunggu lock lain
- Karena saling menunggu, akhirnya terjadi deadlock, keadaan dimana `semua thread tidak berjalan karena hanya menunggu lock`

# Cara Menangani Deadlock

- Sayangnya tidak ada cara menyelesaikan masalah deadlock secara otomatis di Java
- Masalah deadlock harus diselesaikan sendiri oleh programmer yang membuat kode program nya