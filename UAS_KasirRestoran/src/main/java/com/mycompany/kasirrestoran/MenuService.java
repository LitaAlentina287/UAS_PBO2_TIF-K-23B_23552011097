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

    public void tambahMenu(Menu menu) {
        String sql = "INSERT INTO menu (nama, harga, jenis) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, menu.getNama());
            stmt.setDouble(2, menu.getHarga());
            stmt.setString(3, menu.getJenis());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public void updateMenu(Menu menu) {
        String sql = "UPDATE menu SET nama = ?, harga = ?, jenis = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, menu.getNama());
            stmt.setDouble(2, menu.getHarga());
            stmt.setString(3, menu.getJenis());
            stmt.setInt(4, menu.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusMenu(int id) {
        String sql = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
