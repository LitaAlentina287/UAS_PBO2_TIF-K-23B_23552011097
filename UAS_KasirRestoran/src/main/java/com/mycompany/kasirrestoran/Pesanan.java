package com.mycompany.kasirrestoran;

public class Pesanan {
    private int id;
    private int meja;
    private String namaMakanan;
    private double totalHarga;
    private String status;
    private String username; // ditambahkan

    public Pesanan(int id, int meja, String namaMakanan, double totalHarga, String status) {
        this.id = id;
        this.meja = meja;
        this.namaMakanan = namaMakanan;
        this.totalHarga = totalHarga;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getMeja() {
        return meja;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
