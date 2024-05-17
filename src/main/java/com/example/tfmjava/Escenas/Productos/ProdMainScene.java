package com.example.tfmjava.Escenas.Productos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdMainScene implements Initializable {
    public ObservableList<Producto> listaProductos;
    public Button addProductoId;
    public Button editProductoId;
    public Button delProductoId;
    @FXML
    private TableView<Producto> tablaProducto;
    @FXML
    private TableColumn colCodProducto;
    @FXML
    private TableColumn colNombreProducto;
    @FXML
    private TableColumn colMarcaProducto;
    @FXML
    private TableColumn colStock;

    public void onProductoAddClick(ActionEvent actionEvent) {
    }

    public void onProductoEditClick(ActionEvent actionEvent) {
    }

    public void onProductoDelClick(ActionEvent actionEvent) {
    }
    @FXML
    void stockDown(ActionEvent event) {
    }

    @FXML
    void stockUp(ActionEvent event) {

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCodProducto.setCellValueFactory(new PropertyValueFactory<>("cod_prod"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarcaProducto.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableRefresh();
    }

    private void tableRefresh() {
        ObservableList<Producto> productos = FXCollections.observableList(ProductoDAO.listarProductos());
        tablaProducto.setItems(productos);
    }

}
