package com.example.tfmjava.Objetos.util;

import java.security.PublicKey;
import java.sql.*;
public class DataBaseManager{
    public static String username;
    public static String password;
    public static String path = "jdbc:mysql://localhost:3306/";
    public static String dbName;

    public static Connection getConnection(){
        Connection con = null;
        String url = path+dbName;
        try {
            con = DriverManager.getConnection(username, password, url);
        }catch (SQLException e){
            System.out.println("Error de base de datos");
        }
        return con;
    }

    public static void closeConnection(Connection connection){
        try{
            if (connection.isClosed()){
                System.out.println("Conexion ya cerrada");
            } else {
                connection.close();
            }
        }catch (SQLException e){
        }

    }
}
