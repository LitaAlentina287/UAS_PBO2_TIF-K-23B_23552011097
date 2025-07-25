package com.mycompany.kasirrestoran;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegisterWindow {
    private LoginWindow loginWindow;
    private UserService userService = new UserService();

    public RegisterWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
    }

    public void show(Stage stage) {
        Label lblTitle = new Label("Register Akun Baru");
        lblTitle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label lblUsername = new Label("Username:");
        lblUsername.setStyle("-fx-font-size: 16px;");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(350);
        usernameField.setPrefHeight(40);
        usernameField.setStyle("-fx-font-size: 16px;");

        Label lblPassword = new Label("Password:");
        lblPassword.setStyle("-fx-font-size: 16px;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(350);
        passwordField.setPrefHeight(40);
        passwordField.setStyle("-fx-font-size: 16px;");

        Button btnRegister = new Button("Daftar");
        btnRegister.setPrefWidth(160);
        btnRegister.setStyle(
            "-fx-background-color: #27ae60; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 12; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12 40;"
        );

        Button btnBack = new Button("Kembali");
        btnBack.setPrefWidth(160);
        btnBack.setStyle(
            "-fx-background-color: #e67e22; " +
            "-fx-text-fill: white; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 12; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12 40;"
        );

        btnRegister.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Username dan Password tidak boleh kosong!");
                alert.showAndWait();
                return;
            }

            boolean success = userService.register(username, password);
            if (!success) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Username sudah terdaftar atau terjadi kesalahan!");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registrasi berhasil! Silakan login.");
            alert.showAndWait();

            new LoginWindow().show(stage);
        });

        btnBack.setOnAction(e -> new LoginWindow().show(stage));

        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(20);
        formGrid.setAlignment(Pos.CENTER);

        formGrid.add(lblUsername, 0, 0);
        formGrid.add(usernameField, 1, 0);
        formGrid.add(lblPassword, 0, 1);
        formGrid.add(passwordField, 1, 1);

        HBox buttonsBox = new HBox(20, btnRegister, btnBack);
        buttonsBox.setAlignment(Pos.CENTER);
        formGrid.add(buttonsBox, 0, 2, 2, 1);

        VBox container = new VBox(30, lblTitle, formGrid);
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(40));
        container.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        container.setEffect(new DropShadow(15, Color.rgb(0, 0, 0, 0.3)));

        StackPane root = new StackPane(container);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f8ff, #cce7ff);");
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 600, 460);
        stage.setScene(scene);
        stage.setTitle("Register Akun Baru");
        stage.show();
    }
}
