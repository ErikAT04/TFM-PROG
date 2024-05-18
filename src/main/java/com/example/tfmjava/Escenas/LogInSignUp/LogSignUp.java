package com.example.tfmjava.Escenas.LogInSignUp;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

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
        Image img = escenarioActual.getIcons().get(0);
        stage.getIcons().add(img);
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
        Image img = escenarioActual.getIcons().get(0);
        stage.getIcons().add(img);
        controller.initVariables(escenarioActual); //Esto lo hago para pasar al controlador la escena actual.
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.showAndWait();
    }
    @FXML
    void generateDUMMY(ActionEvent event) {
        Usuario usuario = UsuarioDAO.userForLogin("DUMMY");
        if (usuario != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("DUMMY ya creado");
            alert.showAndWait();
        } else {
            try{
                Scanner sc = new Scanner(new File("src/main/java/com/example/tfmjava/SQL/dummySQL1.sql"));
                Connection con = UsuarioDAO.conectarSignUp();

                while (sc.hasNextLine()){
                    String str = sc.nextLine();
                    if (str.contains(";")){
                        con.prepareStatement(str).executeUpdate();
                        str="";
                    }
                }

                DataBaseManager.username = "DUMMY";
                DataBaseManager.password = "PRU3B4";
                DataBaseManager.dbName = "dummybbdd";
                con = DataBaseManager.getConnection();
                sc = new Scanner(new File("src/main/java/com/example/tfmjava/SQL/SQLMain.sql"));
                String str="";
                while (sc.hasNextLine()){
                    str += sc.nextLine();
                    if (str.contains(";")){
                        con.prepareStatement(str).executeUpdate();
                        str="";
                    }
                }

                con = DataBaseManager.getConnection();
                sc = new Scanner(new File("src/main/java/com/example/tfmjava/SQL/dummySQL2.sql"));
                while (sc.hasNextLine()){
                    str += sc.nextLine();
                    if (str.contains(";")){
                        con.prepareStatement(str).executeUpdate();
                        str="";
                    }
                }
                con.close();
                sc.close();

                con.close();

                Stage stage = (Stage) this.loginBT.getScene().getWindow();
                stage.setTitle("HerToq - Menú Principal");
                Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
                stage.getIcons().add(img);
                FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("MainView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void loginAsDUMMY(ActionEvent event) {
        Usuario usuario = UsuarioDAO.userForLogin("DUMMY");
        if (usuario != null) {
            DataBaseManager.username = "DUMMY";
            DataBaseManager.password = "PRU3B4";
            DataBaseManager.dbName = "dummybbdd";
            Stage stage = (Stage) this.loginBT.getScene().getWindow();
            stage.setTitle("HerToq - Menú principal");
            Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
            stage.getIcons().add(img);
            FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("MainView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se ha creado ningún DUMMY");
            alert.showAndWait();
        }
    }
}
