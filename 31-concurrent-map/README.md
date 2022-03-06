# Concurrent Map

- ConcurrentMap merupakan turunan dari Map yang thread safe, dan cocok jika memang diakses oleh lebih dari satu thread
- Tidak ada hal yang spesial dari ConcurrentMap. Semua operasi method nya sama seperti Map
- Yang membedakan dengan Map adalah pada ConcurrentMap dijamin thread safe
- Implementasi dari interface ConcurrentMap adalah class ConcurrentHashMap

# Konversi dari Java Collection

- Pada kasus tertentu, kadang kita tetap butuh menggunakan Java Collection. Namun butuh menggunakan `multiple thread`
- Untuk kasus seperti itu, disarankan `mengubah Java Collection menjadi synchronized` menggunakan helper method `Collections.synchronized...(collection)`