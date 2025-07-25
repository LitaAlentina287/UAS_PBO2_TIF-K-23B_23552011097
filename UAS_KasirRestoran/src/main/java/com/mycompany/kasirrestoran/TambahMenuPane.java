package com.mycompany.kasirrestoran;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class TambahMenuPane extends VBox {

    public TambahMenuPane() {
        setPadding(new Insets(20));
        setSpacing(10);

        // Judul form
        Text title = new Text("Tambah Menu");

        // Input nama menu
        TextField namaField = new TextField();
        namaField.setPromptText("Nama Menu");

        // Input harga
        TextField hargaField = new TextField();
        hargaField.setPromptText("Harga");

        // ComboBox untuk jenis menu (Makanan / Minuman)
        ComboBox<String> jenisComboBox = new ComboBox<>();
        jenisComboBox.getItems().addAll("Makanan", "Minuman");
        jenisComboBox.setPromptText("Pilih Jenis");

        // Tombol Simpan
        Button simpanButton = new Button("Simpan");

        // Label status untuk menampilkan hasil simpan
        Label statusLabel = new Label();

        // Event saat tombol "Simpan" ditekan
        simpanButton.setOnAction(e -> {
            String nama = namaField.getText().trim();
            String hargaText = hargaField.getText().trim();
            String jenis = jenisComboBox.getValue();

            // Validasi input kosong
            if (nama.isEmpty() || hargaText.isEmpty() || jenis == null) {
                statusLabel.setText("Semua field harus diisi.");
                return;
            }

            try {
                double harga = Double.parseDouble(hargaText);

                if (harga <= 0) {
                    statusLabel.setText("Harga harus lebih dari 0.");
                    return;
                }

                // Buat objek menu berdasarkan jenis
                Menu menu;
                if (jenis.equalsIgnoreCase("Makanan")) {
                    menu = new Makanan(nama, harga);
                } else {
                    menu = new Minuman(nama, harga);
                }

                // Simpan ke database
                MenuService menuService = new MenuService();
                menuService.tambahMenu(menu);

                // Reset form & tampilkan pesan sukses
                statusLabel.setText("Menu berhasil disimpan!");
                namaField.clear();
                hargaField.clear();
                jenisComboBox.getSelectionModel().clearSelection();

            } catch (NumberFormatException ex) {
                statusLabel.setText("Harga harus berupa angka.");
            } catch (Exception ex) {
                ex.printStackTrace();
                statusLabel.setText("Terjadi kesalahan saat menyimpan.");
            }
        });

        // Tambahkan semua komponen ke VBox
        getChildren().addAll(title, namaField, hargaField, jenisComboBox, simpanButton, statusLabel);

        // Tambahkan margin agar tampilan lebih rapi
        for (javafx.scene.Node node : getChildren()) {
            if (node instanceof Region) {
                VBox.setMargin(node, new Insets(5, 0, 5, 0));
            }
        }
    }
}
