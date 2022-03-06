# Concurrent Collection

- Java menyediakan Collection yang banyak digunakan untuk proses concurrent programming
- Tidak seperti kebanyakan Collection di package java.util
- Collection di package java.util.concurrent memang di khususkan untuk concurrent programming, sehingga dia thread safe

# Jenis Concurrent Collection

Secara garis besar, concurrent collection di Java terbagi menjadi dua interface

1. BlockingQueue, merupakan turunan dari Queue, dan dikhususkan untuk tipe collection FIFO (First In First Out), seperti di ThreadPool Queue
2. ConcurrentMap, merupakan turunan dari Map, dan dikhususkan untuk Map yang thread safe dibanding implementasi Map di Java Collection