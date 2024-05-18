package com.example.tfmjava.Escenas.MenuOpciones;

import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ChangeUname {

    @FXML
    private TextField passwdField;

    @FXML
    private TextField unameTField;

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
            } else {
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
    }
    @FXML
    public void onPasswdInfoIMGClick(){
        Validator.mostrarInfoPasswd();
    }
    @FXML
    public void onUnameInfoIMGClick(){

    }

}

