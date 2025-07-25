package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

public class HapusPesananPane extends VBox {

    private final PesananService pesananService;
    private final TableView<Pesanan> tableView;

    public HapusPesananPane() {
        setSpacing(10);
        setPadding(new Insets(20));

        pesananService = new PesananService();

        Label label = new Label("Hapus Pesanan");

        tableView = new TableView<>();
        TableColumn<Pesanan, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));

        TableColumn<Pesanan, Integer> mejaCol = new TableColumn<>("No Meja");
        mejaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getMeja()));

        TableColumn<Pesanan, String> namaMakananCol = new TableColumn<>("Nama Makanan");
        namaMakananCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNamaMakanan()));

        TableColumn<Pesanan, Double> totalHargaCol = new TableColumn<>("Total Harga");
        totalHargaCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTotalHarga()));

        TableColumn<Pesanan, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        tableView.getColumns().addAll(idCol, mejaCol, namaMakananCol, totalHargaCol, statusCol);
        refreshTable();

        Button hapusButton = new Button("Hapus");
        hapusButton.setOnAction(e -> {
            Pesanan selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                pesananService.hapusPesanan(selected.getId());
                refreshTable();
                showAlert("Sukses", "Pesanan berhasil dihapus.");
            } else {
                showAlert("Peringatan", "Pilih pesanan terlebih dahulu.");
            }
        });

        getChildren().addAll(label, tableView, hapusButton);
    }

    private void refreshTable() {
        ObservableList<Pesanan> data = FXCollections.observableArrayList(pesananService.getAllPesanan());
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
