package com.mycompany.kasirrestoran;

public class DetailPesanan {
    private Menu menu;
    private int jumlah;

    public DetailPesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    // Untuk menampilkan nama makanan di tabel
    public String getNamaMenu() {
        return menu.getNama();
    }

    // Untuk menampilkan jenis makanan/minuman di tabel (jika perlu)
    public String getJenis() {
        return menu.getJenis();
    }

    // Untuk menghitung total harga per item
    public double getTotalHarga() {
        return menu.getHarga() * jumlah;
    }
}
