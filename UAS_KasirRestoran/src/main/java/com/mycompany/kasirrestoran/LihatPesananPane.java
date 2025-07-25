package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class LihatPesananPane extends BorderPane {

    private TableView<Pesanan> table;
    private ObservableList<Pesanan> data;
    private PesananService pesananService;

    public LihatPesananPane() {
        this.pesananService = new PesananService();

        Label title = new Label("Daftar Semua Pesanan");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        table = new TableView<>();
        setupTable();

        VBox vbox = new VBox(10, title, table);
        vbox.setPadding(new Insets(20));

        this.setCenter(vbox);

        loadData();
    }

    private void setupTable() {
        TableColumn<Pesanan, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Pesanan, String> colNamaMakanan = new TableColumn<>("Nama Makanan");
        colNamaMakanan.setCellValueFactory(new PropertyValueFactory<>("namaMakanan"));

        TableColumn<Pesanan, Double> colHarga = new TableColumn<>("Total Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("totalHarga"));

        TableColumn<Pesanan, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Pesanan, Integer> colMeja = new TableColumn<>("No Meja");
        colMeja.setCellValueFactory(new PropertyValueFactory<>("meja"));

        table.getColumns().addAll(colId, colNamaMakanan, colHarga, colStatus, colMeja);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void loadData() {
        List<Pesanan> pesananList = pesananService.getAllPesanan();
        data = FXCollections.observableArrayList(pesananList);
        table.setItems(data);
    }
}
