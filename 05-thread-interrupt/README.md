# Thread Interrupt

- Interrupt adalah suatu aksi mengirim sinyal ke thread, bahwa Thread tersebut harus berhenti melakukan pekerjaannya saat ini
- Untuk melakukan interrupt, kita bisa menggunakan method interrupt()
- Saat method `interrupt()` dipanggil, maka secara otomatis `Thread.interrupted()` akan bernilai true
- Perlu diingat, kode program kita pada Runnable harus melakukan pengecekan `interrupted`. Jika tidak ada pengecekan, maka sinyal interrupt tidak ada gunanya