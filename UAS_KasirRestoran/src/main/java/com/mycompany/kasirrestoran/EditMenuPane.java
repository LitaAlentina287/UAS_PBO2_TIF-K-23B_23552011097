package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class EditMenuPane extends VBox {

    private final TableView<Menu> tableView;
    private final TextField namaField;
    private final TextField hargaField;
    private final ComboBox<String> jenisComboBox;
    private final ObservableList<Menu> menuList;
    private final MenuService menuService;

    public EditMenuPane() {
        setPadding(new Insets(20));
        setSpacing(10);

        menuService = new MenuService();
        menuList = FXCollections.observableArrayList(menuService.getAllMenu());

        // Table
        tableView = new TableView<>();
        tableView.setItems(menuList);

        TableColumn<Menu, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Menu, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Menu, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        TableColumn<Menu, String> colJenis = new TableColumn<>("Jenis");
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenis"));

        tableView.getColumns().addAll(colId, colNama, colHarga, colJenis);

        // Input fields
        namaField = new TextField();
        namaField.setPromptText("Nama Menu");

        hargaField = new TextField();
        hargaField.setPromptText("Harga");

        jenisComboBox = new ComboBox<>();
        jenisComboBox.getItems().addAll("Makanan", "Minuman");
        jenisComboBox.setPromptText("Pilih Jenis");

        Button updateButton = new Button("Update Menu");
        Label statusLabel = new Label();

        updateButton.setOnAction(e -> {
            Menu selectedMenu = tableView.getSelectionModel().getSelectedItem();
            if (selectedMenu != null) {
                String nama = namaField.getText();
                String hargaText = hargaField.getText();
                String jenis = jenisComboBox.getValue();

                if (nama.isEmpty() || hargaText.isEmpty() || jenis == null) {
                    statusLabel.setText("Semua field harus diisi.");
                    return;
                }

                try {
                    double harga = Double.parseDouble(hargaText);

                    // Buat objek baru sesuai jenis
                    Menu updatedMenu;
                    if ("Makanan".equalsIgnoreCase(jenis)) {
                        updatedMenu = new Makanan(selectedMenu.getId(), nama, harga);
                    } else {
                        updatedMenu = new Minuman(selectedMenu.getId(), nama, harga);
                    }

                    menuService.updateMenu(updatedMenu);
                    statusLabel.setText("Menu berhasil diupdate!");
                    refreshTable();

                } catch (NumberFormatException ex) {
                    statusLabel.setText("Harga harus berupa angka.");
                }
            } else {
                statusLabel.setText("Pilih menu yang ingin diupdate.");
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                namaField.setText(newVal.getNama());
                hargaField.setText(String.valueOf(newVal.getHarga()));
                jenisComboBox.setValue(newVal.getJenis());
            }
        });

        getChildren().addAll(
                new Label("Edit Menu"),
                tableView,
                new Label("Nama"), namaField,
                new Label("Harga"), hargaField,
                new Label("Jenis"), jenisComboBox,
                updateButton,
                statusLabel
        );
    }

    private void refreshTable() {
        menuList.setAll(menuService.getAllMenu());
    }
}
