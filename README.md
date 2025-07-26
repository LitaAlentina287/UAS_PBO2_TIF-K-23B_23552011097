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

### 1. Inheritance (Pewarisan)


Inheritance adalah mekanisme di mana sebuah kelas (subclass atau kelas anak) dapat mewarisi properti dan metode dari kelas lain (superclass atau kelas induk). Ini memungkinkan untuk menggunakan kembali kode dan membangun hierarki "adalah-sebuah" (is-a) antara kelas-kelas.

**Penjelasan dalam Studi Kasus:**

Dalam aplikasi kasir restoran ini, terdapat kelas `Menu` sebagai kelas induk `(superclass)`. Kemudian, kelas `Makanan` dan `Minuman` dibuat sebagai kelas anak `(subclass)` yang mewarisi dari `Menu`. Ini berarti `Makanan` "adalah sebuah" Menu, dan `Minuman` juga "adalah sebuah" `Menu`. Keduanya berbagi properti dan perilaku umum dari Menu (seperti ID, nama, harga), sambil memiliki karakteristik spesifiknya sendiri (jenis "Makanan" atau "Minuman").

**Penerapan dalam Aplikasi :**

```Java
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

    // Metode ini akan menjadi kandidat polymorphism
    public double hitungHarga() {
        return this.harga;
    }
}
```

---


```Java
// Makanan.java (Kelas Anak / Subclass)
package com.mycompany.kasirrestoran;

public class Makanan extends Menu {
    public Makanan(int id, String nama, double harga) {
        super(id, nama, harga, "Makanan"); // Memanggil konstruktor Menu dengan jenis "Makanan"
    }

    public Makanan(String nama, double harga) {
        super(nama, harga, "Makanan"); // Memanggil konstruktor Menu dengan jenis "Makanan"
    }

    @Override
    public String getJenis() {
        return "Makanan";
    }

    // Contoh override metode hitungHarga jika ada diskon khusus makanan, dll.
    @Override
    public double hitungHarga() {
        // Misalnya, makanan tidak ada diskon khusus, jadi mengembalikan harga dasar
        return super.hitungHarga();
    }
}
```

---


```Java
// Minuman.java (Kelas Anak / Subclass)
package com.mycompany.kasirrestoran;

public class Minuman extends Menu {
    public Minuman(int id, String nama, double harga) {
        super(id, nama, harga, "Minuman"); // Memanggil konstruktor Menu dengan jenis "Minuman"
    }

    public Minuman(String nama, double harga) {
        super(nama, harga, "Minuman"); // Memanggil konstruktor Menu dengan jenis "Minuman"
    }

    @Override
    public String getJenis() {
        return "Minuman";
    }

    // Contoh override metode hitungHarga jika ada pajak khusus minuman, dll.
    @Override
    public double hitungHarga() {
        // Misalnya, minuman dikenakan pajak 10%
        return super.hitungHarga() * 1.10;
    }
}
```

---


### 2. Encapsulation (Enkapsulasi)


Encapsulation adalah konsep membungkus data (variabel) dan metode (fungsi) yang beroperasi pada data tersebut menjadi satu unit (kelas). Ini juga melibatkan pembatasan akses langsung ke beberapa komponen objek, sehingga data internal dilindungi dari modifikasi eksternal yang tidak sah. Akses ke data biasanya diberikan melalui metode getter dan setter.

Penjelasan dalam Studi Kasus:
Informasi pemesanan, yang direpresentasikan dalam kelas Pesanan dan DetailPesanan, menerapkan enkapsulasi secara ketat. Variabel-variabel anggota seperti id, meja, totalHarga, status di kelas Pesanan, serta pesanan_id, menu_id, dan jumlah di DetailPesanan, dinyatakan sebagai private. Ini berarti variabel-variabel tersebut hanya bisa diakses dan dimodifikasi melalui metode public (getter dan setter) yang disediakan oleh kelas masing-masing. Enkapsulasi ini memastikan bahwa data pemesanan tetap konsisten dan aman dari akses atau perubahan yang tidak terkontrol.

Kode Relevan:

Java

// Pesanan.java (contoh enkapsulasi)
package com.mycompany.kasirrestoran;

public class Pesanan {
    private int id; // Private
    private int meja; // Private
    private String namaMakanan; // Private
    private double totalHarga; // Private
    private String status; // Private
    private String username; // Private

    public Pesanan(int id, int meja, String namaMakanan, double totalHarga, String status) {
        this.id = id;
        this.meja = meja;
        this.namaMakanan = namaMakanan;
        this.totalHarga = totalHarga;
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public int getMeja() { return meja; }
    public String getNamaMakanan() { return namaMakanan; }
    public double getTotalHarga() { return totalHarga; }
    public String getStatus() { return status; }
    public String getUsername() { return username; }

    // Setters
    public void setStatus(String status) { this.status = status; }
    public void setUsername(String username) { this.username = username; }
}
3. Polymorphism (Polimorfisme)
Polymorphism berarti "banyak bentuk". Dalam OOP, ini memungkinkan objek dari kelas yang berbeda untuk diperlakukan sebagai objek dari kelas umum mereka. Ini dicapai melalui method overriding (implementasi metode di subclass yang sudah ada di superclass) atau method overloading (memiliki beberapa metode dengan nama yang sama tetapi parameter yang berbeda).

Penjelasan dalam Studi Kasus:
Polimorfisme diilustrasikan dengan baik melalui metode Menu::hitungHarga(). Meskipun semua Makanan dan Minuman adalah tipe Menu, masing-masing dapat memiliki cara perhitungan harga yang berbeda atau tambahan. Dengan mendeklarasikan metode hitungHarga() di kelas Menu (sebagai superclass) dan meng-override-nya di kelas Makanan dan Minuman (sebagai subclass), kode dapat memanggil hitungHarga() pada objek Menu secara umum, dan sistem akan secara otomatis mengeksekusi implementasi yang benar (spesifik untuk Makanan atau Minuman) saat runtime. Ini memungkinkan fleksibilitas dalam perhitungan harga tanpa perlu mengetahui jenis spesifik objek Menu saat pemanggilan metode.

Kode Relevan:

Java

// MenuService.java (contoh penggunaan polymorphism pada hitungHarga)
package com.mycompany.kasirrestoran;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final Connection conn;

    public MenuService() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal terhubung ke database.");
        }
    }

    public List<Menu> getAllMenu() {
        List<Menu> daftarMenu = new ArrayList<>();
        String sql = "SELECT * FROM menu";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                double harga = rs.getDouble("harga");
                String jenis = rs.getString("jenis");

                Menu menu;
                if ("Makanan".equalsIgnoreCase(jenis)) {
                    menu = new Makanan(id, nama, harga);
                } else {
                    menu = new Minuman(id, nama, harga);
                }
                daftarMenu.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return daftarMenu;
    }

    // Contoh penggunaan metode hitungHarga secara polimorfik
    public double hitungTotalHargaPesanan(List<DetailPesanan> detailPesananList) {
        double total = 0;
        for (DetailPesanan dp : detailPesananList) {
            // Memanggil hitungHarga() pada objek Menu.
            // Implementasi spesifik (Makanan atau Minuman) akan dipanggil secara otomatis.
            total += dp.getMenu().hitungHarga() * dp.getJumlah();
        }
        return total;
    }

    // Metode lain...
}
Saat hitungTotalHargaPesanan dipanggil, dp.getMenu().hitungHarga() akan memicu metode hitungHarga() yang tepat, baik dari kelas Makanan atau Minuman, berdasarkan jenis objek Menu yang sebenarnya.

4. Abstraction (Abstraksi)
Abstraction adalah konsep menyembunyikan detail implementasi yang kompleks dari pengguna dan hanya menampilkan fungsionalitas esensial. Ini berfokus pada "apa yang dilakukan" daripada "bagaimana itu dilakukan". Abstraksi dapat dicapai melalui kelas abstrak dan antarmuka (interfaces).

Penjelasan dalam Studi Kasus:
Konsep abstraksi diterapkan melalui kelas ItemMakanan. Diasumsikan ItemMakanan akan menjadi kelas abstract yang mendefinisikan perilaku umum untuk item-item yang dapat dimakan, seperti getNama(), getHarga(), tetapi mungkin mewajibkan subclass untuk menyediakan implementasi spesifik untuk metode seperti siapkan(), yang bisa berbeda antara makanan utama dan makanan penutup.

Selain itu, Service Classes seperti MenuService, PesananService, dan UserService adalah contoh lain dari abstraksi. Ketika BuatPesananPane ingin menyimpan pesanan, kelas tersebut hanya memanggil pesananService.tambahPesanan(). BuatPesananPane tidak perlu mengetahui detail rumit bagaimana PesananService membuka koneksi database, membuat prepared statement, menjalankan transaksi untuk menyimpan pesanan dan detailnya. Detail-detail implementasi database ini di-"abstrak" dari lapisan UI, sehingga UI hanya perlu berinteraksi dengan API yang sederhana dan jelas.

Contoh Struktur Kelas Abstrak (Hipotesis untuk ItemMakanan):
(Catatan: Kelas ItemMakanan tidak ada dalam kode yang diberikan, ini adalah contoh hipotesis berdasarkan prompt)

Java

// ItemMakanan.java (Contoh Kelas Abstract)
package com.mycompany.kasirrestoran;

// Misalkan ini adalah kelas abstrak yang diperluas oleh Makanan atau kategori makanan lainnya
public abstract class ItemMakanan extends Menu {
    public ItemMakanan(int id, String nama, double harga) {
        super(id, nama, harga, "Makanan"); // Tetap set jenis dasar
    }

    public ItemMakanan(String nama, double harga) {
        super(nama, harga, "Makanan");
    }

    // Metode abstrak yang harus diimplementasikan oleh subclass
    // Contoh: setiap jenis makanan mungkin punya cara penyajian berbeda
    public abstract String getInstruksiPenyajian();

    // Metode konkret yang bisa diwarisi
    public void cetakDeskripsi() {
        System.out.println("Nama: " + getNama() + ", Harga: " + getHarga());
    }
}

// Makanan.java akan memperluas ItemMakanan jika ItemMakanan adalah abstrak
/*
public class Makanan extends ItemMakanan {
    // ... konstruktor
    @Override
    public String getInstruksiPenyajian() {
        return "Disajikan hangat dengan saus khusus.";
    }
    // ... lainnya
}
*/
Kode Relevan (Konsep Abstraksi melalui Service Layer):

Java

// BuatPesananPane.java (menggunakan abstraksi dari PesananService)
package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class BuatPesananPane extends VBox {

    // ... (variabel lain)
    private final PesananService pesananService; // Ini adalah abstraksi!

    public BuatPesananPane() {
        // ... (inisialisasi UI)

        pesananService = new PesananService(); // Inisialisasi service

        // ... (kode lainnya)

        Button simpanButton = new Button("Simpan Pesanan");
        simpanButton.setOnAction(e -> {
            // ... (validasi input)

            int idPesanan = pesananService.tambahPesanan(pesanan, new ArrayList<>(pesananSementara));
            // UI tidak perlu tahu detail SQL atau manajemen koneksi.
            // Cukup panggil method 'tambahPesanan' dan PesananService yang mengurus implementasinya.

            // ... (handling hasil simpan)
        });

        // ... (tambahan komponen ke VBox)
    }

    // ... (metode lain)
}
Dalam contoh di atas, BuatPesananPane hanya berinteraksi dengan PesananService melalui metode publiknya (tambahPesanan). Detail internal tentang bagaimana tambahPesanan berkomunikasi dengan database, menangani transaksi, atau mengelola koneksi database sepenuhnya tersembunyi dari BuatPesananPane. Ini adalah abstraksi yang sangat penting, membuat kode lebih bersih, lebih mudah dipahami, dan lebih mudah dipelihara.



---


## Demo Proyek

* **GitHub**: \[Link repositori GitHub Anda]
* **YouTube**: \[https://youtu.be/RfRL2ekUCoo?si=lGS_QjA2VAbmGWUV]

---

