# ThreadLocalRandom

- Sebelumnya kita sering menggunakan class Random untuk membuat angka random
- Saat menggunakan object Random secara parallel, maka di dalam class Random kita akan melakukan  sharing(berbagi) variable antar thread. Hal ii membuat class Random tidak aman dan juga lambat
- Oleh karena itu terdapat class ThreadLocalRandom
- ThreadLocalRandom merupakan class yang seperti ThreadLocal. Namun spesial untuk Random, sehingga kita bisa membuat angka random tanpa khawatir dengan race condition, karena object Random nya akan berbeda tiap thread

# ThreadLocalRandrom Stream

- Dia memiliki fitur untuk `membuat random number secara stream`
- Hal ini mempermudah kita ketika ingin melakukan random number tanpa harus pusing membuat perulangan secara manual
- Ada banyak method di ThreadLocalRandom seperti `inst()`, `longs()`, dan `doubles()` yang mengembalikan data stream