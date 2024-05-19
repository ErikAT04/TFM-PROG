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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

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

    @FXML
    private Label passwdContador;

    @FXML
    private Label unameContador;

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
        String passwd = passRegister.getText();
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
                if (existsInDB(uname)){
                    alert.setTitle("Error de usuario");
                    alert.setContentText("Ya existe un usuario con ese nombre\nUtilice otro nombre");
                    alert.showAndWait();
                } else {
                    if (!Validator.validarPasswd(passwd) || !Validator.validarUname(uname)){
                        alert.setContentText("La contraseña o el nombre de usuario no siguen los criterios esperados");
                    } else {
                        UsuarioDAO.register(new Usuario(uname, passwd));
                        DataBaseManager.firstConnection();
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
        }
        alert.showAndWait();
    }
    public boolean existsInDB(String uname){
        Usuario u = UsuarioDAO.userForLogin(uname);
        return u!=null;
    }
    public void initVariables(Stage stage){
        this.escenario = stage;
    }
    public void cambioEscenaAMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InitApplication.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        escenario.setTitle("HerToq - Menú principal");
        Image img = new Image(Objects.requireNonNull(InitApplication.class.getResource("otherSRC/img/favicon.png")).toString());
        escenario.getIcons().add(img);
        this.escenario.setScene(scene);
        this.escenario.show();
    }

    @FXML
    void onUnameInfoIIMGClick(MouseEvent event) {
        Validator.mostrarInfoUname();
    }

    @FXML
    void onUnameType(KeyEvent event) {
        int num = unameRegister.getText().length();
        if (num>30){
            unameRegister.setText(unameRegister.getText().substring(0, 29));
        }
        unameContador.setText(num + "/30");
        if (num == 30){
            unameContador.setTextFill(Color.RED);
        } else  {
            unameContador.setTextFill(Color.GREY);
        }
    }

    @FXML
    void onPasswdInfoIMGClick(MouseEvent event) {
        Validator.mostrarInfoPasswd();
    }

    @FXML
    void onPasswdType(KeyEvent event) {
        int num = passRegister.getText().length();
        if (num>30){
            passRegister.setText(passRegister.getText().substring(0, 29));
        }
        if (num == 30){
            passwdContador.setTextFill(Color.RED);
        } else  {
            passwdContador.setTextFill(Color.GREY);
        }
        passwdContador.setText(num + "/30");
    }
}

