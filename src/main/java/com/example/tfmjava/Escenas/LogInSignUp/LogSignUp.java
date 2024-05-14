package com.example.tfmjava.Escenas.LogInSignUp;

import com.example.tfmjava.InitApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LogSignUp {

    @FXML
    private Button loginBT;

    @FXML
    private Button registerBT;

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("LogInSignUp/LogInOpt.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LogInOpt controller = fxmlLoader.getController();
        Stage escenarioActual = (Stage) this.loginBT.getScene().getWindow();
        controller.initVariables(escenarioActual); //Esto lo hago para pasar al controlador la escena actual.
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.showAndWait();
    }
    @FXML
    void onRegisterClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("LogInSignUp/SignUpOpt.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SignUpOpt controller = fxmlLoader.getController();
        Stage escenarioActual = (Stage) this.loginBT.getScene().getWindow();
        controller.initVariables(escenarioActual); //Esto lo hago para pasar al controlador la escena actual.
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
