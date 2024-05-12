package com.example.tfmjava.Escenas.LogInSignUp;

import com.example.tfmjava.InitApplication;
import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SignUpOpt {

    @FXML
    private PasswordField passRegister;

    @FXML
    private Label passTXT;

    @FXML
    private PasswordField repPassRegister;

    @FXML
    private TextField unameRegister;
    @FXML
    private CheckBox acceptCB;

    Stage escenario;

    @FXML
    void checkIfSame() {
        if (passRegister.getText().isEmpty()) {
            passTXT.setTextFill(Color.RED);
            passTXT.setText("Primero escriba una contraseña");
        } else if (!passRegister.getText().equals(repPassRegister.getText())) {
            passTXT.setTextFill(Color.RED);
            passTXT.setText("Las contraseñas no coinciden");
        } else if (repPassRegister.getText().isEmpty()) {
            passTXT.setText("");
        } else {
            passTXT.setTextFill(Color.GREEN);
            passTXT.setText("Las contraseñas coinciden");
        }
    }
    @FXML
    void onRegisterAction(ActionEvent event) {
        boolean done = false;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);

        String uname = unameRegister.getText();
        String passwd = unameRegister.getText();
        if (uname.isEmpty() || passwd.isEmpty()){
            alert.setTitle("Error de campos");

            alert.setContentText("Rellena todos los campos");
        } else if (!acceptCB.isSelected()){
            alert.setTitle("Sin confirmación aparente");
            alert.setContentText("Debes aceptar nuestros términos y condiciones");
        } else {
            if (!passRegister.getText().equals(repPassRegister.getText())){
                alert.setTitle("Error de contraseñas");
                alert.setContentText("Las contraseñas no coinciden, corrige ese error");
            } else {
                if (existsInDB(uname, passwd)){
                    alert.setTitle("Error de usuario");
                    alert.setContentText("Ya existe un usuario con ese nombre\nUtilice otro nombre");
                    alert.showAndWait();
                } else {
                    UsuarioDAO.register(new Usuario(uname, passwd));
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Conexión creada");
                    alert.setContentText("Bienvenido a su nueva base de datos, " + DataBaseManager.username);

                    Stage escena = (Stage) this.acceptCB.getScene().getWindow();
                    escena.close();
                    try {
                        cambioEscenaAMain();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        alert.showAndWait();
    }
    public boolean existsInDB(String uname, String passwd){
        Boolean bool;
        Usuario u = UsuarioDAO.checkForLogin(uname, passwd);
        return u!=null;
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
}

