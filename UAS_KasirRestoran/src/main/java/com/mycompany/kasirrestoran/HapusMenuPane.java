package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class HapusMenuPane extends VBox {

    private final MenuService menuService;
    private final TableView<Menu> tableView;

    public HapusMenuPane() {
        setSpacing(10);
        setPadding(new Insets(20));

        menuService = new MenuService();

        Label label = new Label("Hapus Menu");

        tableView = new TableView<>();
        TableColumn<Menu, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNama()));

        TableColumn<Menu, Double> hargaCol = new TableColumn<>("Harga");
        hargaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getHarga()));

        TableColumn<Menu, String> jenisCol = new TableColumn<>("Jenis");
        jenisCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getJenis()));

        tableView.getColumns().addAll(namaCol, hargaCol, jenisCol);
        refreshTable();

        Button hapusButton = new Button("Hapus");
        hapusButton.setOnAction(e -> {
            Menu selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                menuService.hapusMenu(selected.getId());
                refreshTable();
                showAlert("Sukses", "Menu berhasil dihapus.");
            } else {
                showAlert("Peringatan", "Pilih menu terlebih dahulu.");
            }
        });

        getChildren().addAll(label, tableView, hapusButton);
    }

    private void refreshTable() {
        ObservableList<Menu> data = FXCollections.observableArrayList(menuService.getAllMenu());
        tableView.setItems(data);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
