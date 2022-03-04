# Thread Sleep

- Pada saat proses developmnet, kadang kita butuh melakukan simulasi proses yang berjalan dalam waktu tertentu
- Untuk melakukan hal ini, kita bisa memanfaatkan fitur Thread Sleep yang terdapat di Java
- Dengan menggunakan Thread Sleep, kita bisa membuat thread tertidur dan berhenti dalam waktu yang kita tentukan
- Untuk melakukan hal ini, kita bisa memanggil static method sleep() di class Thread, maka secara otomatis Thread saat itu dimana memanggil memanggil kode sleep() akan tertidur sesuai dengan waktu millisecond yang sudah kita masukkan dalam parameter
- Namun perlu diperhatikan, method sleep bisa menyebabkan error InterruptedException