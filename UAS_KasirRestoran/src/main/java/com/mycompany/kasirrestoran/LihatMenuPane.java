package com.mycompany.kasirrestoran;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class LihatMenuPane extends VBox {
    public LihatMenuPane() {
        MenuService menuService = new MenuService();
        ObservableList<Menu> menuList = FXCollections.observableArrayList(menuService.getAllMenu());

        TableView<Menu> tableView = new TableView<>();
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

        this.getChildren().add(tableView);
        this.setSpacing(10);
    }
}
