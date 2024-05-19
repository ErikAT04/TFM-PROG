package com.example.tfmjava.Escenas.Productos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ProdSubScene {
    @FXML
    private TextField nombreTF;
    @FXML
    private TextField marcaTF;
    @FXML
    private TextField stockTF;
    @FXML
    private Button sendBtt;
    @FXML
    private Label nombreCaracteres;
    @FXML
    private Label marcaCaracteres;
    Producto prevProd;
    boolean editar = false;
    @FXML
    public void onSendClick(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String accion = (editar)? " editado " : " añadido ";
        if (nombreTF.getText().isEmpty()||marcaTF.getText().isEmpty()||stockTF.getText().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error de contenido");
            alert.setContentText("Alguno de los campos se ha quedado sin rellenar");
            alert.showAndWait();
        } else {
            try {
                int numFilasAfec;
                String nombre = nombreTF.getText();
                String marca = marcaTF.getText();
                int stock = Integer.parseInt(stockTF.getText());
                if (stock<0){
                    alert.setContentText("No puede haber stock negativo");
                } else {
                    Producto producto = new Producto(stock, marca, nombre);
                    if (editar){
                        numFilasAfec = ProductoDAO.ActualizarProducto(producto);
                    } else {
                        numFilasAfec = ProductoDAO.addProducto(producto);
                    }
                    if (numFilasAfec==1){
                        alert.setContentText("Producto" + accion + "correctamente");
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        Stage stage = (Stage) this.sendBtt.getScene().getWindow();
                        stage.close();
                    } else {
                        alert.setContentText("No se ha podido llevar a cabo la acción correctamente");
                    }
                }
            }catch (Exception e) {
                alert.setTitle("Error de conversión");
                alert.setContentText("En el campo de 'Stock' tiene que haber valores numéricos");
            }
            alert.showAndWait();
        }
    }

    @FXML
    void onMarcaType(KeyEvent event) {
        String s = marcaTF.getText();
        if (marcaTF.getText().length()>30){
            s = s.substring(0,29);
        }
        if (s.length()==30){
            marcaCaracteres.setTextFill(Color.RED);
        }else {
            marcaCaracteres.setTextFill(Color.GREY);
        }
        marcaCaracteres.setText(s.length() + "/30");
    }

    @FXML
    void onNombreType(KeyEvent event) {
        String s = nombreTF.getText();
        if (marcaTF.getText().length() > 40){
            s = s.substring(0,40);
        }
        if (s.length()==40){
            nombreCaracteres.setTextFill(Color.RED);
        }else {
            nombreCaracteres.setTextFill(Color.GREY);
        }
        marcaCaracteres.setText(s.length() + "/40");
    }

    public void toEdit(Producto prod){
        this.prevProd = prod;
        editar = true;
        nombreTF.setText(prod.getNombre());
        marcaTF.setText(prod.getMarca());
    }
}
