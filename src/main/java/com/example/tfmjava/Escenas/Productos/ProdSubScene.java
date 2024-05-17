package com.example.tfmjava.Escenas.Productos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ProdSubScene {
    @FXML
    private TextField nombreTF;
    @FXML
    private TextField marcaTF;
    @FXML
    private TextField stockTF;

    public void onEditarClick(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (nombreTF.getText().isEmpty()||marcaTF.getText().isEmpty()||stockTF.getText().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error de contenido");
            alert.setContentText("Alguno de los campos se ha quedado sin rellenar");
            alert.showAndWait();
        } else {
            try {
                String nombre = nombreTF.getText();
                String marca = marcaTF.getText();
                int stock = Integer.parseInt(stockTF.getText());
                Producto producto = new Producto(stock, marca, nombre);
                int numFilasAfec = ProductoDAO.addProducto(producto);
            }catch (Exception e) {
                alert.setHeaderText(null);
                alert.setTitle("Error de conversión");
                alert.setContentText("En el campo de 'Stock' tiene que haber valores numéricos");
                alert.showAndWait();
            }
        }
    }

    public void loadItems(Producto prod){
        nombreTF.setText(prod.getNombre());
        marcaTF.setText(prod.getMarca());
        marcaTF.setText(prod.getNombre());
    }
}
