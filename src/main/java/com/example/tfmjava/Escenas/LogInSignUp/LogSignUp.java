package com.example.tfmjava.Escenas.LogInSignUp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LogSignUp {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}