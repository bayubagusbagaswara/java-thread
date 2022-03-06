# Semaphore

- Class yang digunakan untuk manage data counter
- Nilai counter bisa naik, namun ada batas maksimalnya. Jika batas maksimalnya sudah tercapai, maka semua thread yang akan mencoba menaikkan counter harus menunggu, sampai ada thread lain yang menurunkan nilai counter tersebut
- Semaphore cocok sekali untuk menjaga agar thread berjalan pada maksimal total counter yang sudah kita tentukan