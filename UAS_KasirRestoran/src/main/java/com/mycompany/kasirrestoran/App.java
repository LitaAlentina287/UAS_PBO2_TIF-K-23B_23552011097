package com.mycompany.kasirrestoran;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Langsung buka LoginWindow
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
