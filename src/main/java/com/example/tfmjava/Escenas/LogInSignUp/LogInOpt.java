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
import javafx.stage.Stage;

import java.io.IOException;

public class LogInOpt {

    @FXML
    private Button loginBtt;

    @FXML
    private PasswordField passRegister;

    @FXML
    private Label passTXT;

    @FXML
    private TextField unameRegister;
    Stage escenario;

    @FXML
    void onLoginAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        if (unameRegister.getText().isEmpty()|| passRegister.getText().isEmpty()){
            alert.setTitle("Error de contenido");
            alert.setContentText("Alguno de los contenedores está vacío");
        } else {
            String uname = unameRegister.getText();
            String passwd = passRegister.getText();
            Usuario u2 = UsuarioDAO.userForLogin(uname);
            if (u2==null || !passwd.equals(u2.getPasswd())){
                alert.setTitle("Error de inicio de sesión");
                alert.setContentText("Los datos introducidos no son correctos");
            }  else {
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
        alert.showAndWait();
    }
    public void initVariables(Stage stage){
        this.escenario = stage;
    }
    public void cambioEscenaAMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.escenario.setScene(scene);
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
}
