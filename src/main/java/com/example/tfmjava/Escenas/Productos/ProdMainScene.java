package com.example.tfmjava.Escenas.Productos;

import com.example.tfmjava.Objetos.DAO.ProductoDAO;
import com.example.tfmjava.Objetos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdMainScene implements Initializable{
    public ObservableList<Producto> listaProductos;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn colCod;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colStock;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaProductos = FXCollections.observableArrayList();
        ArrayList<Producto> productos = ProductoDAO.listarProductos();
        listaProductos.addAll(productos);
        colCod.setCellValueFactory(new PropertyValueFactory<>("cod_prod"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
}
