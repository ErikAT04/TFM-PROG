package com.example.tfmjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InitApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("LogInSignUp/LogInSignUpOpt.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inserte nombre de aplicacion aqui");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}