# CompletableFuture

- Pada Java 8, terdapat sebuah class baru bernama `CompletableFuture`, 
- ini merupakan implementasi dari Future yang bisa kita `set datanya secara manual`. Jadi tidak berbasis Callable
- CompatableFuture ini sangat cocok ketika kita misal perlu `membuat future secara manual`, sehingga kita tidak memerlukan Callable
- Untuk memberi value terhadap CompletableFuture secara manual, kita bisa menggunakan method `complete(value)` atau `completeExceptionally(error)` untuk error

# CompletionStage

- CompletableFuture merupakan turunan dari interface CompletionStage
- CompletionStage merupakan fitur dimana kita bisa menambahkan asynchronous computation, tanpa harus menunggu dulu data dari Future nya ada
- CompletionStage sangat mirip dengan operation di Java Stream, hanya saja tidak sekomplit di Java Stream