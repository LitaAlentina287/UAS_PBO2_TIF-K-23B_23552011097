package com.mycompany.kasirrestoran;

public class Minuman extends Menu {
    public Minuman(int id, String nama, double harga) {
        super(id, nama, harga, "Minuman");
    }

    public Minuman(String nama, double harga) {
        super(nama, harga, "Minuman");
    }

    @Override
    public String getJenis() {
        return "Minuman";
    }
}
