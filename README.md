# Final Proyek Pemrograman Berorientasi Obyek 2

- **Mata Kuliah**: Pemrograman Berorientasi Obyek 2
- **Dosen Pengampu**: Muhammad Ikhwan Fathulloh



## Profil  
- **Nama**: Lita Alentina  
- **NIM**: 23552011097  
- **Studi** Kasus: Kasir Restoran



## Judul Studi Kasus

Aplikasi Kasir Restoran Berbasis JavaFX



## Penjelasan Studi Kasus

Aplikasi ini merupakan sistem kasir restoran berbasis JavaFX yang memungkinkan Kasir untuk mengelola menu makanan dan minuman serta melakukan pengelolaan pesanan pelanggan. Semua tampilan dirancang langsung menggunakan kode Java, dengan penambahan efek visual seperti gradasi warna dan drop shadow.
Database yang digunakan adalah MySQL dan pengelolaan dependensi dilakukan melalui Maven (`pom.xml`).

## Fitur Aplikasi

* Login 
* Register
* Tambah Menu 
* Lihat Menu
* Edit Menu
* Buat Pesanan
* Bayar Pesanan
* Lihat Pesanan
* Hapus Menu
* Hapus Pesanan
* Logout

## Role Pengguna

Kasir (User)
* Mendaftar lewat fitur Register
* Login dengan akun terdaftar
* Mengelola seluruh aktivitas kasir


## Teknologi yang Digunakan

* JavaFX 
* Java JDK 21
* Apache NetBeans 23
* MySQL (via Laragon)
* Maven (pom.xml)

---

## Penjelasan 4 Pilar OOP dalam Studi Kasus

1. **Inheritance (Pewarisan)**
   Kelas-kelas dalam aplikasi seperti `Pesanan`, `DetailPesanan`, dan `User` mewarisi atribut umum dan memperluas fungsionalitasnya untuk digunakan dalam berbagai fitur.

2. **Encapsulation (Enkapsulasi)**
   Semua atribut diakses melalui method getter dan setter untuk menjaga keamanan data serta memudahkan pengelolaan data objek.

3. **Polymorphism (Polimorfisme)**
   Method yang sama seperti `tampilkanData()` digunakan secara fleksibel di berbagai tampilan dan objek, menyesuaikan dengan jenis data dan konteks penggunaannya.

4. **Abstraction (Abstraksi)**
   Logika seperti login, pengolahan pesanan, dan pengelolaan menu disimpan dalam service class terpisah seperti `UserService`, `MenuService`, dan `PesananService`, menyederhanakan antarmuka pengguna.

---


## Demo Proyek

* **GitHub**: \[Link repositori GitHub Anda]
* **YouTube**: \[https://youtu.be/RfRL2ekUCoo?si=lGS_QjA2VAbmGWUV]

---

