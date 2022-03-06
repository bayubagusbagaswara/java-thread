# CompletionService

- CompletionService merupkan sebuah interface yang digunakan `untuk memisahkan` antara bagian yang mengeksekusi asynchronous task dan yang menerima hasil dari task yang sudah selesai
- Kadang ada kebutuhan misal kita butuh menjalankan sesuatu secara parallel, lalu ada satu thread yang melakukan eksekusi task dan satu thread menunggu hasilnya
- Kita bisa menggunakan CompletionService untuk melakukan itu
- Implementasi interface CompletionService adalah class `ExecutorCompletionService`
- Yang mengembalikan hasil bentuknya itu adalah Future dan task nya itu dalam bentuk Callable