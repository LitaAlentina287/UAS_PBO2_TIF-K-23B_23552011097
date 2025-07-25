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

**Fitur Aplikasi**

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

**Role Pengguna**

Kasir (User)
* Mendaftar lewat fitur Register
* Login dengan akun terdaftar
* Mengelola seluruh aktivitas kasir


**Teknologi yang Digunakan**

* JavaFX 
* Java JDK 21
* Apache NetBeans 23
* MySQL (via Laragon)
* Maven (pom.xml)

---

## 4 Pilar OOP dalam Aplikasi Kasir Restoran

Konsep **Pemrograman Berorientasi Objek (OOP)** merupakan dasar penting dalam pembuatan aplikasi saat ini. Dalam aplikasi kasir restoran ini, keempat prinsip utama OOP digunakan secara nyata untuk membangun program yang rapi serta mudah dikembangkan.

---

## 1.Inheritance (Pewarisan)

**Definisi:**

Inheritance adalah mekanisme di mana sebuah kelas anak (_subclass_) mewarisi properti dan metode dari kelas induk (_superclass_). Konsep ini memungkinkan kode dapat digunakan kembali dan membentuk hubungan "adalah sebuah" (_is-a_) antar objek.

**Penerapan dalam Aplikasi:**

- Kelas `Menu` bertindak sebagai **superclass**.
- Kelas `Makanan` dan `Minuman` bertindak sebagai **subclass** dari `Menu`.
- Artinya, baik `Makanan` maupun `Minuman` adalah bentuk dari `Menu`, dan mewarisi atribut seperti `id`, `nama`, `harga`, dan `jenis`.

**Contoh Kode:**

```
// Menu.java (Kelas Induk / Superclass)
package com.mycompany.kasirrestoran;

public class Menu {
    private int id;
    private String nama;
    private double harga;
    private String jenis; // Makanan / Minuman

    public Menu(int id, String nama, double harga, String jenis) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
    }

    public Menu(String nama, double harga, String jenis) {
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
    }

    // Getters
    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getJenis() { return jenis; }

    // Setters (jika diperlukan)
    public void setId(int id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setJenis(String jenis) { this.jenis = jenis; }
}

---

```
// Makanan.java (Kelas Anak / Subclass)
package com.mycompany.kasirrestoran;

public class Makanan extends Menu {
    public Makanan(int id, String nama, double harga) {
        super(id, nama, harga, "Makanan"); // Memanggil konstruktor Menu dengan jenis "Makanan"
    }

    public Makanan(String nama, double harga) {
        super(nama, harga, "Makanan"); // Memanggil konstruktor Menu dengan jenis "Makanan"
    }

    @Override // Opsional, hanya untuk memperjelas bahwa ini override perilaku dari superclass
    public String getJenis() {
        return "Makanan";
    }
}

---

## 2️⃣ Encapsulation (Enkapsulasi)

**🧩 Definisi:**

Enkapsulasi adalah proses membungkus data dan metode dalam satu unit (kelas), serta membatasi akses langsung ke data tersebut untuk menjaga keamanannya. Akses biasanya diberikan melalui _getter_ dan _setter_.

**📌 Penerapan dalam Aplikasi:**

- Kelas seperti `Menu`, `Pesanan`, dan `DetailPesanan` menggunakan atribut `private`.
- Akses terhadap data hanya bisa dilakukan melalui metode publik seperti `getNama()`, `setHarga()`, dll.
- Mencegah data penting (seperti harga) dimodifikasi secara langsung dari luar kelas.

✅ **Contoh Kode:**
Sudah tepat, tidak memerlukan perubahan.

---

## 3️⃣ Polymorphism (Polimorfisme)

**🧩 Definisi:**

Polimorfisme memungkinkan objek dari subclass diperlakukan sebagai objek dari superclass-nya. Dengan begitu, kode menjadi lebih fleksibel dan dinamis tanpa harus tahu tipe spesifik setiap objek.

**📌 Penerapan dalam Aplikasi:**

- Objek bertipe `Makanan` dan `Minuman` disimpan dalam list bertipe `Menu`.
- Ketika memanggil `getJenis()` dari objek di dalam list, Java akan secara otomatis memilih implementasi metode dari subclass yang tepat.

✅ **Contoh Kode:**
Sudah representatif dalam `MenuService.java`.

---

## 4️⃣ Abstraction (Abstraksi)

**🧩 Definisi:**

Abstraksi menyembunyikan detail implementasi yang kompleks dan hanya menampilkan antarmuka (interface) yang penting. Fokus utamanya adalah **apa yang dilakukan**, bukan **bagaimana melakukannya**.

**📌 Penerapan dalam Aplikasi:**

- Kelas `Menu` menyederhanakan interaksi pengguna dengan data makanan/minuman.
- Service layer (`MenuService`, `PesananService`, `UserService`) menyembunyikan logika database dari UI.
- Komponen UI hanya memanggil metode seperti `pesananService.tambahPesanan()` tanpa mengetahui detail SQL yang digunakan.

✅ **Contoh Kode:**
Sudah sesuai dan mencerminkan penerapan abstraksi melalui service layer.

---

📌 **Kesimpulan:**

Aplikasi kasir restoran ini menerapkan keempat prinsip utama OOP secara seimbang dan fungsional. Dengan struktur berbasis OOP, aplikasi menjadi:

- Lebih mudah dikembangkan dan diperluas.
- Lebih terorganisir dan dapat dipelihara dalam jangka panjang.
- Lebih aman dalam mengelola data penting dan alur logika.



---


## Demo Proyek

* **GitHub**: \[Link repositori GitHub Anda]
* **YouTube**: \[https://youtu.be/RfRL2ekUCoo?si=lGS_QjA2VAbmGWUV]

---

