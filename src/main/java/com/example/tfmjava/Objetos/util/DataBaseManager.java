package com.example.tfmjava.Objetos.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.sql.*;
import java.util.Scanner;

public class DataBaseManager{
    public static String username;
    public static String password;
    public static String path = "jdbc:mysql://83.53.117.100:3306/";
    public static String dbName;

    public static Connection getConnection(){
        Connection con = null;
        String url = path+dbName;
        try {
            con = DriverManager.getConnection(url, username, password);
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
    public static void firstConnection(){
        try(Connection con = getConnection()){
            try (Scanner sc = new Scanner(new File("src/main/java/com/example/tfmjava/SQL/SQLMain.sql"))) {
                String sql = "";
                while (sc.hasNext()) {
                    sql += sc.nextLine();
                    if (sql.contains(";")){
                        PreparedStatement sentencia = con.prepareStatement(sql);
                        sentencia.executeUpdate();
                        sql ="";
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
