# Locks Package

- Ini adalah alternatif dari `low level synchronized dan manual wait dan notify`
- Untuk saat ini, sangat disarankan menggunakan package locks dibandingkan menggunakan low level synchronization

# ReadWriteLock

- Kadang ada kondisi dimana kita ingin membedakan lock antara operasi update dan operasi get
- Untuk kasus seperti ini, kita bisa membuat dua buah variable Lock
- Namun, di Java disediakan cara yang lebih mudah, yaitu menggunakan interface ReadWriteLock
- ReadWriteLock merupakan lock yang mendukung dua jenis operasi, read dan write
- Implementasi dari interface ReadWriteLock adalah class ReentrantReadWriteLock

# Condition Interface

- Condition merupakan alternatif lain dari monitor method (wait, notify, dan notifyAll)
- Pada Java modern saat ini, sangat disarankan menggunakan Condition dibanding monitor method
- Condition memiliki method wait() untuk menunggu, signal() untuk mentrigger satu thread, dan signalAll() untuk mentrigger semua thread yang menunggu
- Cara pembuatan Condition, kita bisa menggunakan method newCondition() milik Lock