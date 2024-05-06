package com.example.tfmjava.Objetos.util;

import java.security.PublicKey;
import java.sql.*;
public class DataBaseManager{
    public static Connection dataBaseCon;
    public static String username;
    public static String password;
    public static String path = "jdbc::mysql:localhost";
    public static String extra;
    public static Connection getConnection(){
        Connection con = null;
        String url = path+extra+"db";
        try {
            con = DriverManager.getConnection(username, password, url);

        }catch (SQLException e){
            System.out.println("Error de base de datos");
        }
        return con;
    }

    public void closeConnection(Connection connection){
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
