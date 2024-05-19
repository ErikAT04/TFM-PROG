package com.example.tfmjava.Escenas.LogInSignUp;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogInOpt {

    @FXML
    private Button loginBtt;

    @FXML
    private PasswordField passLogin;

    @FXML
    private Label passTXT;

    @FXML
    private TextField unameLogin;
    Stage escenario;
    @FXML
    private Label passwdContador;

    @FXML
    private Label unameContador;

    @FXML
    void onLoginAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        if (unameLogin.getText().isEmpty() || passLogin.getText().isEmpty()) {
            alert.setTitle("Error de contenido");
            alert.setContentText("Alguno de los contenedores está vacío");
        } else {
            if (!Validator.validarPasswd(passLogin.getText()) || !Validator.validarUname(unameLogin.getText())) {
                alert.setContentText("La contraseña o el nombre de usuario no siguen los criterios esperados");
            } else {
                String uname = unameLogin.getText();
                String passwd = passLogin.getText();
                Usuario u2 = UsuarioDAO.userForLogin(uname);
                if (u2 == null || !passwd.equals(u2.getPasswd())) {
                    alert.setTitle("Error de inicio de sesión");
                    alert.setContentText("Los datos introducidos no son correctos");
                } else {
                    DataBaseManager.username = uname;
                    DataBaseManager.password = passwd;
                    DataBaseManager.dbName = u2.getDb();
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Inicio de sesión");
                    alert.setHeaderText("Sesión iniciada correctamente");
                    alert.setContentText("Bienvenido de vuelta, " + uname);
                    try {
                        cambioEscenaAMain();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage escenario = (Stage) this.passTXT.getScene().getWindow();
                    escenario.close();
                }
            }
        }
        alert.showAndWait();
    }
    public void initVariables(Stage stage){
        this.escenario = stage;
    }
    public void cambioEscenaAMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("MainView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        this.escenario.setScene(scene);
        escenario.setTitle("ToqHer - Menú principal");
        Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
        escenario.getIcons().add(img);
        this.escenario.show();
    }
    @FXML
    void passwdInfoIMGClick(javafx.scene.input.MouseEvent mouseEvent) {
        Validator.mostrarInfoPasswd();
    }
    @FXML
    public void unameInfoIMGClick(javafx.scene.input.MouseEvent mouseEvent) {
        Validator.mostrarInfoUname();
    }

    @FXML
    void onPasswdType(KeyEvent event) {
        int num = passLogin.getText().length();
        if (num>30){
            passLogin.setText(passLogin.getText().substring(0, 29));
            num = passLogin.getText().length();
        }
        if (num == 30){
            passwdContador.setTextFill(Color.RED);
        } else  {
            passwdContador.setTextFill(Color.GREY);
        }
        passwdContador.setText(num + "/30");
    }

    @FXML
    void onUnameType(KeyEvent event) {
        int num = unameLogin.getText().length();
        if (num>30){
            unameLogin.setText(unameLogin.getText().substring(0, 29));
            num = unameLogin.getText().length();
        }
        unameContador.setText(num + "/30");
        if (num == 30){
            unameContador.setTextFill(Color.RED);
        } else  {
            unameContador.setTextFill(Color.GREY);
        }
    }
}
