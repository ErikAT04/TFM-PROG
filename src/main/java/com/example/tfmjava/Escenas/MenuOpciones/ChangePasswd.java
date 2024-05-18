package com.example.tfmjava.Escenas.MenuOpciones;

import com.example.tfmjava.Objetos.LogInSignUp.Usuario;
import com.example.tfmjava.Objetos.LogInSignUp.UsuarioDAO;
import com.example.tfmjava.Objetos.util.DataBaseManager;
import com.example.tfmjava.Objetos.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.sql.DriverManager;

public class ChangePasswd {

    @FXML
    private PasswordField newPassConfirmTField;

    @FXML
    private PasswordField newPassTField;

    @FXML
    private PasswordField thisPassTField;
    @FXML
    private Label passLabel;

    @FXML
    void onChangePasswordField(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de contraseña");
        alert.setHeaderText(null);
        if (newPassConfirmTField.getText().isEmpty() || thisPassTField.getText().isEmpty() || newPassTField.getText().isEmpty()){
            alert.setContentText("Alguno de los campos está vacío");
        } else if (!newPassTField.getText().equals(newPassConfirmTField.getText())){
            alert.setContentText("Las contraseñas no coinciden");
        } else if (!newPassTField.getText().equals(DataBaseManager.password)) {
            alert.setContentText("La contraseña original no es correcta");
        } else if (!Validator.validarPasswd(newPassTField.getText())){
            alert.setContentText("La contraseña no cumple el criterio esperado.");
        } else {
            String newPass = newPassTField.getText();
            Usuario usuario = UsuarioDAO.userForLogin(DataBaseManager.username);
            usuario.setPasswd(newPass);
            int numFilas = UsuarioDAO.updateUser(usuario);
            if(numFilas==1){
                numFilas=UsuarioDAO.updatePasswd(usuario);
                if (numFilas==1){
                    alert.setContentText("Contraseña cambiada correctamente");
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cambio correcto");
                    DataBaseManager.password=newPass;
                }else {
                    alert.setContentText("La contraseña no se ha podido cambiar");
                }
            } else {
                alert.setContentText("Ha habido un problema a la hora de cambiar la contraseña");
            }
        }
        alert.showAndWait();
    }

    @FXML
    void onRepeatPassKeyTyped(KeyEvent event) {
        if (newPassTField.getText().equals(newPassConfirmTField.getText())){
            passLabel.setText("Las contraseñas coinciden");
            passLabel.setTextFill(Color.GREEN);
        } else {
            passLabel.setText("Las contraseñas no coinciden");
            passLabel.setTextFill(Color.RED);
        }
    }
    @FXML
    public void onInfoIMGView(MouseEvent mouseEvent) {
        Validator.mostrarInfoPasswd();
    }
}
