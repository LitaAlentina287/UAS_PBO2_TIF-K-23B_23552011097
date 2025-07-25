package com.mycompany.kasirrestoran;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomeWindow {
    private final String username;

    public HomeWindow(String username) {
        this.username = username;
    }

    public void show(Stage stage) {
        stage.setTitle("Beranda Kasir Restoran");

        Label welcomeLabel = new Label("Selamat datang, " + username + "!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #0D47A1;");

        StackPane contentArea = new StackPane();
        contentArea.setPadding(new Insets(15));
        contentArea.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");

        Button btnTambahMenu = new Button("Tambah Menu");
        Button btnLihatMenu = new Button("Lihat Menu");
        Button btnEditMenu = new Button("Edit Menu");
        Button btnBuatPesanan = new Button("Buat Pesanan");
        Button btnBayarPesanan = new Button("Bayar Pesanan");
        Button btnLihatPesanan = new Button("Lihat Pesanan");
        Button btnHapusMenu = new Button("Hapus Menu");
        Button btnHapusPesanan = new Button("Hapus Pesanan");
        Button btnLogout = new Button("Logout");

        Button[] navButtons = {
            btnTambahMenu, btnLihatMenu, btnEditMenu, btnBuatPesanan,
            btnBayarPesanan, btnLihatPesanan, btnHapusMenu, btnHapusPesanan, btnLogout
        };

        for (Button btn : navButtons) {
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setStyle(
                "-fx-background-color: #1565C0;" +   // Biru tua
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;" +
                "-fx-padding: 8 12;"
            );
        }

        btnTambahMenu.setOnAction(e -> contentArea.getChildren().setAll(new TambahMenuPane()));
        btnLihatMenu.setOnAction(e -> contentArea.getChildren().setAll(new LihatMenuPane()));
        btnEditMenu.setOnAction(e -> contentArea.getChildren().setAll(new EditMenuPane()));
        btnBuatPesanan.setOnAction(e -> contentArea.getChildren().setAll(new BuatPesananPane()));
        btnBayarPesanan.setOnAction(e -> contentArea.getChildren().setAll(new BayarPesananPane()));
        btnLihatPesanan.setOnAction(e -> contentArea.getChildren().setAll(new LihatPesananPane()));
        btnHapusMenu.setOnAction(e -> contentArea.getChildren().setAll(new HapusMenuPane()));
        btnHapusPesanan.setOnAction(e -> contentArea.getChildren().setAll(new HapusPesananPane()));
        btnLogout.setOnAction(e -> {
            stage.close();
            new LoginWindow().show(new Stage());
        });

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #B3E5FC;"); // Sidebar biru muda
        sidebar.getChildren().addAll(navButtons);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #E0F7FA;"); // Background utama biru muda
        root.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        BorderPane.setMargin(welcomeLabel, new Insets(10));
        root.setLeft(sidebar);
        root.setCenter(contentArea);

        Scene scene = new Scene(root, 900, 550);
        stage.setScene(scene);
        stage.show();
    }
}
