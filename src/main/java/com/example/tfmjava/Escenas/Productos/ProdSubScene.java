package com.example.tfmjava.Escenas.Productos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    public void toEdit(Producto prod){
        this.prevProd = prod;
        editar = true;
        nombreTF.setText(prod.getNombre());
        marcaTF.setText(prod.getMarca());
        marcaTF.setText(prod.getNombre());
    }
}
