package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class BuatPesananPane extends VBox {

    private final TableView<Menu> tableMenu;
    private final TableView<DetailPesanan> tablePesanan;
    private final ObservableList<Menu> daftarMenu;
    private final ObservableList<DetailPesanan> pesananSementara;
    private final PesananService pesananService;

    public BuatPesananPane() {
        MenuService menuService = new MenuService();
        pesananService = new PesananService();
        daftarMenu = FXCollections.observableArrayList(menuService.getAllMenu());
        pesananSementara = FXCollections.observableArrayList();

        // --- Tabel daftar menu ---
        Label labelMenu = new Label("Daftar Menu");
        tableMenu = new TableView<>();
        tableMenu.setItems(daftarMenu);

        TableColumn<Menu, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Menu, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Menu, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<Menu, String> colJenis = new TableColumn<>("Jenis");
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenis"));

        tableMenu.getColumns().addAll(colId, colNama, colHarga, colJenis);

        // --- Form input jumlah dan nomor meja ---
        Label jumlahLabel = new Label("Jumlah:");
        TextField jumlahField = new TextField();
        jumlahField.setPromptText("Masukkan jumlah");

        Label mejaLabel = new Label("No. Meja:");
        TextField mejaField = new TextField();
        mejaField.setPromptText("Masukkan nomor meja");

        Button tambahButton = new Button("Tambah ke Pesanan");
        tambahButton.setOnAction(e -> {
            Menu selectedMenu = tableMenu.getSelectionModel().getSelectedItem();
            if (selectedMenu != null) {
                try {
                    int jumlah = Integer.parseInt(jumlahField.getText());
                    if (jumlah > 0) {
                        DetailPesanan detail = new DetailPesanan(selectedMenu, jumlah);
                        pesananSementara.add(detail);
                        jumlahField.clear();
                        showAlert("Sukses", "Menu berhasil ditambahkan ke pesanan.");
                    } else {
                        showAlert("Kesalahan", "Jumlah harus lebih dari 0.");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Kesalahan", "Jumlah harus berupa angka.");
                }
            } else {
                showAlert("Peringatan", "Pilih menu terlebih dahulu.");
            }
        });

        VBox formBox = new VBox(5, mejaLabel, mejaField, jumlahLabel, jumlahField, tambahButton);
        formBox.setPadding(new Insets(10));

        // --- Tabel pesanan sementara ---
        Label labelPesanan = new Label("Pesanan Sementara");
        tablePesanan = new TableView<>();
        tablePesanan.setItems(pesananSementara);

        TableColumn<DetailPesanan, String> colPesananNama = new TableColumn<>("Nama");
        colPesananNama.setCellValueFactory(new PropertyValueFactory<>("namaMenu"));

        TableColumn<DetailPesanan, Integer> colJumlah = new TableColumn<>("Jumlah");
        colJumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));

        TableColumn<DetailPesanan, Double> colTotal = new TableColumn<>("Total Harga");
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalHarga"));

        tablePesanan.getColumns().addAll(colPesananNama, colJumlah, colTotal);

        // --- Tombol simpan pesanan ---
        Button simpanButton = new Button("Simpan Pesanan");
        simpanButton.setOnAction(e -> {
            if (pesananSementara.isEmpty()) {
                showAlert("Peringatan", "Pesanan masih kosong.");
                return;
            }

            if (mejaField.getText().isEmpty()) {
                showAlert("Peringatan", "Nomor meja harus diisi.");
                return;
            }

            int nomorMeja;
            try {
                nomorMeja = Integer.parseInt(mejaField.getText());
            } catch (NumberFormatException ex) {
                showAlert("Kesalahan", "Nomor meja harus berupa angka.");
                return;
            }

            double totalHarga = pesananSementara.stream()
                    .mapToDouble(DetailPesanan::getTotalHarga)
                    .sum();

            String namaMakanan = pesananSementara.size() == 1
                    ? pesananSementara.get(0).getNamaMenu()
                    : "Multi Menu";

            Pesanan pesanan = new Pesanan(0, nomorMeja, namaMakanan, totalHarga, "Belum Dibayar");
            pesanan.setUsername("kasir1"); // Atur username default atau dari login

            int idPesanan = pesananService.tambahPesanan(pesanan, new ArrayList<>(pesananSementara));

            if (idPesanan > 0) {
                showAlert("Sukses", "Pesanan berhasil disimpan dengan ID: " + idPesanan);
                pesananSementara.clear();
                mejaField.clear();
            } else {
                showAlert("Error", "Gagal menyimpan pesanan.");
            }
        });

        this.getChildren().addAll(
                labelMenu, tableMenu,
                formBox,
                labelPesanan, tablePesanan,
                simpanButton
        );
        this.setSpacing(10);
        this.setPadding(new Insets(20));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
