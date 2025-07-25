package com.mycompany.kasirrestoran;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWindow {

    private final UserService userService = new UserService();

    public void show(Stage stage) {
        // === Judul ===
        Label lblTitle = new Label("Login Kasir Restoran");
        lblTitle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // === Username ===
        Label lblUsername = new Label("Username:");
        lblUsername.setStyle("-fx-font-size: 16px;");
        TextField txtUsername = new TextField();
        txtUsername.setPrefWidth(350);
        txtUsername.setPrefHeight(40);
        txtUsername.setStyle("-fx-font-size: 16px;");

        // === Password ===
        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-font-size: 16px;");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPrefWidth(350);
        txtPassword.setPrefHeight(40);
        txtPassword.setStyle("-fx-font-size: 16px;");

        // === Tombol Login ===
        Button btnLogin = new Button("Login");
        btnLogin.setPrefWidth(160);
        btnLogin.setStyle(
            "-fx-background-color: #3498db; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 12; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12 40;"
        );

        // === Tombol Register ===
        Button btnRegister = new Button("Register");
        btnRegister.setPrefWidth(160);
        btnRegister.setStyle(
            "-fx-background-color: #2ecc71; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 12; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12 40;"
        );

        // === Aksi Login ===
        btnLogin.setOnAction(event -> {
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Peringatan", "Username dan Password tidak boleh kosong.");
                return;
            }

            boolean success = userService.login(username, password);
            if (success) {
                new HomeWindow(username).show(stage);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Gagal", "Username atau Password salah.");
            }
        });

        // === Aksi Register ===
        btnRegister.setOnAction(event -> new RegisterWindow(this).show(stage));

        // === Layout Form ===
        GridPane formGrid = new GridPane();
        formGrid.setVgap(20);
        formGrid.setHgap(15);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.add(lblUsername, 0, 0);
        formGrid.add(txtUsername, 1, 0);
        formGrid.add(lblPassword, 0, 1);
        formGrid.add(txtPassword, 1, 1);

        HBox buttonBox = new HBox(20, btnLogin, btnRegister);
        buttonBox.setAlignment(Pos.CENTER);
        formGrid.add(buttonBox, 0, 2, 2, 1);

        VBox container = new VBox(30, lblTitle, formGrid);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(40));
        container.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        container.setEffect(new DropShadow(15, Color.rgb(0, 0, 0, 0.3)));

        StackPane root = new StackPane(container);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #cce7ff);");

        Scene scene = new Scene(root, 600, 460);
        stage.setTitle("Login - Kasir Restoran");
        stage.setScene(scene);
        stage.show();
    }

    // === Utilitas Menampilkan Alert ===
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
