package com.example.tfmjava.Escenas.MenuOpciones;

import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ChangeUname {

    @FXML
    private PasswordField passwdField;

    @FXML
    private TextField unameTField;

    @FXML
    private Label passContador;

    @FXML
    private Label unameContador;

    @FXML
    void onChangeField(ActionEvent event) {
        Usuario actualUser = UsuarioDAO.userForLogin(DataBaseManager.username);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        if (passwdField.getText().isEmpty()||unameTField.getText().isEmpty()){
            alert.setContentText("No se ha introducido alguno de los valores");
        } else {
            String uname = unameTField.getText();
            String passwd = passwdField.getText();
            if (!Validator.validarUname(uname) || !Validator.validarPasswd(passwd)){
                alert.setContentText("No se cumplen los criterios de nombre y contraseña.");
            } else if (passwd.equals(DataBaseManager.password)) {
                alert.setContentText("La contraseña escrita no es correcta");
            }else {
                Usuario usuario = UsuarioDAO.userForLogin(uname);
                if (usuario == null){
                    actualUser.setUname(uname);
                    int numFilasAct = UsuarioDAO.updateUser(actualUser);
                    if (numFilasAct==1){
                        DataBaseManager.username = uname;
                        alert.setContentText("Usuario Actualizado correctamente");
                        alert.setTitle("Usuario Actualizado");
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                    } else {
                        alert.setContentText("No se ha podido actualizar");
                    }
                } else {
                    alert.setContentText("Ya existe un usuario con el nombre " + uname);
                }
            }
        }
        alert.showAndWait();
    }
    @FXML
    void onPasswdInfoIMGClick(MouseEvent event) {
        Validator.mostrarInfoPasswd();
    }
    @FXML
    void onUnameInfoIMGClick(MouseEvent event) {
        Validator.mostrarInfoUname();
    }

    public void onUnameType(KeyEvent keyEvent) {
        int num = unameTField.getText().length();
        if (num>30){
            unameTField.setText(unameTField.getText().substring(0, 29));
        }
        unameContador.setText(num + "/30");
        if (num == 30){
            unameContador.setTextFill(Color.RED);
        } else  {
            unameContador.setTextFill(Color.GREY);
        }
    }

    public void onPassType(KeyEvent keyEvent) {
        int num = passwdField.getText().length();
        if (num>30){
            passwdField.setText(passwdField.getText().substring(0, 29));
            num = passwdField.getText().length();
        }
        passContador.setText(num + "/30");
        if (num == 30){
            passContador.setTextFill(Color.RED);
        } else  {
            passContador.setTextFill(Color.GREY);
        }
    }
}

