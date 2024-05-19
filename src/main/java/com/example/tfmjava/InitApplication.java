package com.example.tfmjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InitApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("LogInSignUp/LogInSignUpOpt.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image img = new Image(InitApplication.class.getResource("otherSRC/img/favicon.png").toString());
        stage.getIcons().add(img);
        stage.setTitle("ToqHer Manager");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}