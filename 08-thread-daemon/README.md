# Thread Daemon

- Secara default, saat kita membuat thread, thread tersebut disebut `user thread`
- Java (bukan JUnit) secara default akan selalu menunggu semua `user thread selesai` sebelum program berhenti
- Jika kita mengubah thread menjadi `daemon thread`, menggunakan setDaemon(true), maka secara otomatis thread tersebut menjadi `daemon thread` (bukan lagi user thread)
- Daemon Thread `tidak akan ditunggu` jika memang program Java akan berhenti
- Jadi thread nya langsung di kill atau dimatikan thread nya
- Namun jika kita menghentikan program Java menggunakan System.exit(), maka user thread pun akan otomatis terhenti