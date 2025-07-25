package com.mycompany.kasirrestoran;

import java.sql.*;
import java.util.*;

public class PesananService {

    public List<Pesanan> getAllPesanan() {
        List<Pesanan> pesananList = new ArrayList<>();
        Map<Integer, Pesanan> pesananMap = new HashMap<>();

        String query = "SELECT p.id, p.meja, p.total_harga, p.status, m.nama AS nama_makanan " +
                       "FROM pesanan p " +
                       "JOIN detail_pesanan dp ON p.id = dp.pesanan_id " +
                       "JOIN menu m ON dp.menu_id = m.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");

                if (!pesananMap.containsKey(id)) {
                    int meja = rs.getInt("meja");
                    double totalHarga = rs.getDouble("total_harga");
                    String status = rs.getString("status");
                    String namaMakanan = rs.getString("nama_makanan");

                    Pesanan pesanan = new Pesanan(id, meja, namaMakanan, totalHarga, status);
                    pesananMap.put(id, pesanan);
                    pesananList.add(pesanan);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pesananList;
    }

    public int tambahPesanan(Pesanan pesanan, List<DetailPesanan> detailPesananList) {
        int idPesanan = -1;
        String insertPesanan = "INSERT INTO pesanan (meja, username, total_harga, status) VALUES (?, ?, ?, ?)";
        String insertDetail = "INSERT INTO detail_pesanan (pesanan_id, menu_id, jumlah) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtPesanan = conn.prepareStatement(insertPesanan, Statement.RETURN_GENERATED_KEYS)) {
                stmtPesanan.setInt(1, pesanan.getMeja());
                stmtPesanan.setString(2, pesanan.getUsername());
                stmtPesanan.setDouble(3, pesanan.getTotalHarga());
                stmtPesanan.setString(4, pesanan.getStatus());
                stmtPesanan.executeUpdate();

                ResultSet generatedKeys = stmtPesanan.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idPesanan = generatedKeys.getInt(1);
                }

                try (PreparedStatement stmtDetail = conn.prepareStatement(insertDetail)) {
                    for (DetailPesanan dp : detailPesananList) {
                        stmtDetail.setInt(1, idPesanan);
                        stmtDetail.setInt(2, dp.getMenu().getId());
                        stmtDetail.setInt(3, dp.getJumlah());
                        stmtDetail.addBatch();
                    }
                    stmtDetail.executeBatch();
                }

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idPesanan;
    }

    public void updateStatusPesanan(int id, String statusBaru) {
        String query = "UPDATE pesanan SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, statusBaru);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hapusPesanan(int id) {
        String deleteDetail = "DELETE FROM detail_pesanan WHERE pesanan_id = ?";
        String deletePesanan = "DELETE FROM pesanan WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt1 = conn.prepareStatement(deleteDetail);
                 PreparedStatement stmt2 = conn.prepareStatement(deletePesanan)) {

                stmt1.setInt(1, id);
                stmt1.executeUpdate();

                stmt2.setInt(1, id);
                stmt2.executeUpdate();

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
