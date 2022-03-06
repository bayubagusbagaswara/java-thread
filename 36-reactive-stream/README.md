# Reactive Stream

- Saat ini sudah sangat populer dengan paradigma Concurrency yang bernama reactive programming
- Banyak sekali library untuk reactive programming seperti RxJava, Reactor, Akka Stream dan lain-lain
- Sejak `Java 9`, diperkenalkan fitur Reactive Stream di Java
- Reactive Stream merupakan standar baru untuk `Asynchronous Stream Processing`
- Detail spesifikasinya terdapat di website: 
- http://www.reactive-stream.org/

# Flow

- Dalam Reactive Stream, kita mengenal istilah namanya `Flow` (aliran data). 
- Berbeda dengan yang sebelumnya kita sudah pelajari tentang Thread, dalam Reactive Stream yang difokuskan adalah aliran data
- Dalam aliran data, artinya ada yang mengirim data dan ada yang menerima data
- Pihak yang mengirim data, kita sebut `Publisher`, dan pihak yang menerima data, kita sebut `Subscriber`
- Sebuah aliran data, kita sebut namanya Flow

# Reactive Stream Class

- Implementasi dari Reactive Stream di Java disediakan dalam bentuk class Flow
- Untuk publisher, implementasinya menggunakan interface `Flow.Publisher`
- Dan untuk subscriber, implementasinya menggunakan interface `Flow.Subscriber`

# Buffer

- Saat publisher mengirim data terlalu cepat, maka secara default data akan di buffer 
- Buffer mirip dengan antrian, dimana secara default buffer di Flow ukurannya sekitar FLow.DEFAULT_BUFFER_SIZE (256), artinya jika publisher mengirim data terlalu cepat, maka buffer akan menampung data tersebut dahulu sampai sekitar 256 data, jika buffer sudah penuh, maka publisher harus menunggu sampai data di buffer diambil oleh subscriber
- Jika 256 terlalu besar, maka kita bisa mengatur data buffer yang kita inginkan

# Processor

- Flow memiliki fitur yang bernama Processor yang direpresentasikan dalam interface Processor
- Processor singkatnya adalah gabungan antara Publisher dan Subscriber, jadi dia bisa menerima data dari publisher lain lalu mengirim ke subscriber lain
- Procesor cocok jika kita ingin memanipulasi data publisher, kemudian hasilnya dikirim ke subscriber lain

# Reactive Stream Library

- Reactive Stream sudah menjadi standard device reactive stream programming di Java
- Jadi sekarang jika kita ingin menggunakan library RxJava atau Reactor, semua bisa diintegrasikan dengan Reactive Stream
- https://github.com/ReactiveX/RxJava
- https://github.com/reactor/reactor-core