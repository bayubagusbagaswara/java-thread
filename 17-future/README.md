# Future

# Callable<T>

- Sebelumnya kita selalu menggunakan Runnable untuk mengirim `perintah/task` ke thread
- Namun, pada Runnable, setelah pekerjaannya selesai, tidak ada data yang dikembalikan sama sekali, karena method nya return `void`
- Sedangkan pada asynchronous kadang-kadang kita ingin mengembalikan saat pekerjaan itu sudah selesai
- Callable mirip dengan Runnable. Namun, Callable `mengembalikan data`
- Selain itu, Callable merupakan Generic Type, sehingga kita bisa tentukan tipe return data nya

# Future<T>

- Jika kita ingin mengeksekusi callable, kita bisa menggunakan `method submit(Callable)` pada ExecutorService, 
- method `submit(Callable)` tersebut akan `mengembalikan Future<T>`
- misalnya `Callable<String>`, kemudian kita submit callable nya, maka hasil balikan method submit adalah `Future<String>` atau `Future of String`
- Future merupakan `representasi` data yang akan dikembalikan oleh proses `asynchronous`
- Dengan menggunakan Future, kita bisa mengecek apakah pekerjaan Callable nya sudah selesai atau belum, 
- dan juga mendapat data balikan hasil dari Callable
- Jadi Future<T> mirip seperti `wrapper` (pembungkus) untuk hasil Callable nya

## Catatan

- Callable<T> adalah pengganti Runnable. Callable mengembalikan sebuah data, sedangkan Runnable tidak mengembalikan data
- Future<T> adalah tipe data balikan dari Callable<T>

# Future Method

- `T get()` : mengambil result data, jika belum ada, maka akan menunggu sampai ada
- `T get(timeout, time unit)` : mengambil result data, jika belum ada, maka akan menunggu sampai timeout
- `void cancel(mayInterrupt)` : membatalkan proses callable, dan apakah diperbolehkan di interrupt jika sudah terlanjur berjalan
- `boolean isCancelled()` : mengecek apakah future dibatalkan
- `boolean isDone()` : mengecek apakah future telah selesai

# Invoke All

- ExecutorService memiliki method bernama `invokeAll(Collection<Callable<T>>)` untuk mengeksekusi banyak Callable secara sekaligus
- Ini cocok ketika ada kasus kita ingin menjalankan proses `asynchronous secara parallel` sebanyak jumlah thread di threadpool
- Hal ini bisa mempercepat proses dibanding kita eksekusi satu persatu
- Jadi, task akan dieksekusi berbarengan oleh thread nya, tapi juga tergantung jumlah thread didalam threadpoolnya
- Misalnya ada task 10 dan thread nya juga 10, maka 10 task tersebut akan dirunning bersamaan di thread yang berbeda. Hasilnya pun akan bersamaan
- Singkatnya, invoke all ini menunggu semua task selesai, baru kita ambil

# Invoke Any

- Kadang ada kasus dimana kita ingin mengeksekusi beberapa proses secara asynchronous. Namun ingin `mendapatkan hasil yang paling cepat`
- Hal ini bisa dilakukan dengan menggunakan method `invokeAny(Collection<Callable<T>>)`
- invokeAny() akan mengembalikan result data dari Callable `yang paling cepat mengembalikan result`
- Singkatnya, invoke any ini menunggu yang paling cepat, dan kita ambil data yang paling cepat dibalikan datanya
