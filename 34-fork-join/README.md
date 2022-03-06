# Fork Join

- Fork/Join merupakan fitur yang mulai diperkenalkan di Java 7 untuk membantu mempercepat proses secara parallel memanfaatkan semua CPU Processor. Proses ini dicapai menggunakan teknik `Devide and Conquer`
- Secara sederhana, Fork/Join akan melakukan FORK, memecah pekerjaan menjadi pekerjaan yang lebih kecil dan dieksekusi secara asynchronous
- Setelah proses FORK selesai, proses JOIN akan dilakukan, yaitu menggabungkan hasil semua pekerjaan yang telah selesai menjadi satu hasil
- Fork/Join menggunakan thread pool bernama ForkJoinPool dan menggunakan thread ForkJoinWorkerThread

# Work Stealing Algorithm

- Fork/Join Thread menggunakan algoritma work stealing (mencuri), artinya jika Fork/Join akan secara maksimal menjalankan pekerjaan di semua thread, dan jika ada thread yang sudah selesai, maka thread tersebut akan coba mencuri pekerjaan dari queue thread lain
- Algoritma ini memastikan bahwa semua thread akan bekerja dan pekerjaan diselesaikan secepatnya

# ForkJoinTask

- ForkJoinPool merupakan turunan dari ExecutorService, jadi cara penggunaannya sama dengan ExecutorService sebelumnya yang sudah kita bahas
- Namun agar tujuan dari ForkJoinPool tercapai, baiknya kita menggunakan ForkJoinTask sebagai task yang kita submit ke ForkJoinPool
- ForkJoinTask adalah turunan dari Callable, sehingga kita bisa menggunakan method execute() atau submit() untuk mengirim task ke ForkJoinPool

# RecursiveAction dan RecursiveTask

- ForkJoinTask adalah abstract class, dan terdapat 2 abstract class turunannya yang bisa kita gunakan agar lebih mudah membuat ForkJoinTask
- RecursiveAction, merupakan class yang bisa kita gunakan untuk task yang tidak mengembalikan result seperti Runnable
- RecursiveTask, merupakan class yang bisa kita gunakan untuk task yang mengembalikan result seperti Callable