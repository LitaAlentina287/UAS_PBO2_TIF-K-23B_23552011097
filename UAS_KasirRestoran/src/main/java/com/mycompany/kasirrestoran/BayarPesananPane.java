package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

public class BayarPesananPane extends VBox {

    private final TableView<Pesanan> tableView;
    private final ObservableList<Pesanan> pesananList;
    private final PesananService pesananService;

    public BayarPesananPane() {
        setPadding(new Insets(20));
        setSpacing(10);

        pesananService = new PesananService();
        pesananList = FXCollections.observableArrayList(
            pesananService.getAllPesanan()
                .stream()
                .filter(p -> p.getStatus().equalsIgnoreCase("Belum Dibayar"))
                .collect(Collectors.toList())
        );

        Label title = new Label("Daftar Pesanan Belum Dibayar");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        tableView = new TableView<>();
        tableView.setItems(pesananList);

        TableColumn<Pesanan, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Pesanan, String> colNamaMakanan = new TableColumn<>("Nama Makanan");
        colNamaMakanan.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));

        TableColumn<Pesanan, Double> colTotalHarga = new TableColumn<>("Total Harga");
        colTotalHarga.setCellValueFactory(new PropertyValueFactory<>("totalHarga"));

        TableColumn<Pesanan, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Pesanan, Integer> colMeja = new TableColumn<>("No Meja");
        colMeja.setCellValueFactory(new PropertyValueFactory<>("meja"));

        tableView.getColumns().addAll(colId, colNamaMakanan, colTotalHarga, colStatus, colMeja);

        Button bayarButton = new Button("Bayar Pesanan");
        Label statusLabel = new Label();

        bayarButton.setOnAction(e -> {
            Pesanan selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                pesananService.updateStatusPesanan(selected.getId(), "Sudah Dibayar");
                statusLabel.setText("Pesanan ID " + selected.getId() + " berhasil dibayar.");
                refreshTable();
            } else {
                statusLabel.setText("Silakan pilih pesanan yang ingin dibayar.");
            }
        });

        getChildren().addAll(title, tableView, bayarButton, statusLabel);
    }

    private void refreshTable() {
        pesananList.setAll(
            pesananService.getAllPesanan()
                .stream()
                .filter(p -> p.getStatus().equalsIgnoreCase("Belum Dibayar"))
                .collect(Collectors.toList())
        );
    }
}
