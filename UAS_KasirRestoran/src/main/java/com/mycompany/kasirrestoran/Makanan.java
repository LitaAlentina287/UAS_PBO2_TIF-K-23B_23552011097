package com.mycompany.kasirrestoran;

public class Makanan extends Menu {
    public Makanan(int id, String nama, double harga) {
        super(id, nama, harga, "Makanan");
    }

    public Makanan(String nama, double harga) {
        super(nama, harga, "Makanan");
    }

    @Override
    public String getJenis() {
        return "Makanan";
    }
}
