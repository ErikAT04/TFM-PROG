package com.example.tfmjava.Objetos.LogInSignUp;

import com.example.tfmjava.Objetos.util.DataBaseManager;

import javax.net.ssl.SSLEngine;
import java.sql.*;

public class UsuarioDAO {
    public static Connection conectarLogin(){ //Esta conexión hace referencia a una base de datos concreta, la que guarda usuarios
        Connection con=null;
        String usuario = "root";
        String password = "IESRibera23";
        String url = "jdbc:mysql://83.53.117.100:3306/users";
        try {
            con = DriverManager.getConnection(url, usuario, password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static Connection conectarSignUp(){ //Esta conexión sirve para poder editar el sistema, es necesario para crear usuarios y bases de datos porque dentro de conexiones como la anterior no se puede hacer
        Connection con=null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://83.53.117.100:3306/?user=root&password=IESRibera23");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static int register(Usuario usuario){
        String sqlInsert = "INSERT INTO USUARIO(UNAME, PASSWD) VALUES (?, ?)";
        int numFilas;
        try{
            Connection con = conectarLogin();

            PreparedStatement sentencia = con.prepareStatement(sqlInsert);
            sentencia.setString(1, usuario.getUname());
            sentencia.setString(2, usuario.getPasswd());

            numFilas = sentencia.executeUpdate();

            //Ahora, voy a sacar el nombre de la base de datos como el nombre de usuario y el ID
            if (numFilas==1) {
                String sqlQuery = "SELECT ID FROM USUARIO WHERE UNAME = ?";
                sentencia = con.prepareStatement(sqlQuery);
                sentencia.setString(1, usuario.getUname());

                ResultSet resultado = sentencia.executeQuery();

                if (resultado.next()){
                    int id = resultado.getInt("id");
                    String dbName = usuario.getUname() + id;

                    String sqlUpdate = "UPDATE USUARIO SET DB=? WHERE ID=?";

                    sentencia = con.prepareStatement(sqlUpdate);
                    sentencia.setString(1, dbName);
                    sentencia.setInt(2, id);

                    numFilas = sentencia.executeUpdate();
                    create_Database(dbName, usuario.getUname(), usuario.getPasswd());
                }
            }

            con.close();
        }catch (SQLException e){
            System.out.println("RROR");
            return 0;
        }
        return numFilas;
    }
    public static void create_Database(String dbName, String uname, String passwd){
        String sqlCreateUser = "CREATE USER ?@'%' IDENTIFIED BY ?;";
        String sqlCreateDatabase = "CREATE DATABASE " + dbName;
        String sqlGrant = "GRANT ALL PRIVILEGES ON " +dbName + ".* TO " + uname +";";

        try {
            Connection con = conectarSignUp();

            PreparedStatement sentencia = con.prepareStatement(sqlCreateUser);
            sentencia.setString(1, uname);
            sentencia.setString(2, passwd);

            sentencia.executeUpdate();
            Statement sentenciaNormal = con.createStatement();
            sentenciaNormal.executeUpdate(sqlCreateDatabase);

            sentenciaNormal = con.createStatement();
            sentenciaNormal.executeUpdate(sqlGrant);

            con.close();
            DataBaseManager.password = passwd;
            DataBaseManager.username = uname;
            DataBaseManager.dbName = dbName;
        }catch (SQLException e){
            System.out.println("Error");
        }

    }
    public static Usuario userForLogin(String uname){
        Usuario usuario = null;
        String sqlQuery = "SELECT * FROM USUARIO WHERE UNAME = ?";

        try {
            Connection con = conectarLogin();

            PreparedStatement sentencia = con.prepareStatement(sqlQuery);
            sentencia.setString(1, uname);

            ResultSet consulta = sentencia.executeQuery();

            if (consulta.next()){
                int id = consulta.getInt("id");
                String dB = consulta.getString("DB");
                String userPasswd = consulta.getString("PASSWD");
                usuario = new Usuario(uname, userPasswd, dB, id); //El usuario y la contraseña ya los tengo de antes si la consulta es correcta, sería una tontería volver a crear variables
            }

            con.close();

        }catch (SQLException e){
        }
        return  usuario;
    }
    public static boolean eraseUser(Usuario usuario){
        boolean bool = false;
        String sqlDeleteDB = "DROP DATABASE " + usuario.getDb();
        String sqlDeleteUser = "DROP USER " + usuario.getUname();
        String sqlDeleteFromUsers = "DELETE FROM USUARIO WHERE UNAME = ?";

        try {
            Connection con = conectarSignUp();
            PreparedStatement sentencia = con.prepareStatement(sqlDeleteDB);
            sentencia.executeUpdate();
            sentencia = con.prepareStatement(sqlDeleteUser);
            sentencia.executeUpdate();
            con = conectarLogin();
            sentencia = con.prepareStatement(sqlDeleteFromUsers);
            sentencia.setString(1, usuario.getUname());
            sentencia.executeUpdate();
            con.close();
            bool = true;
        }catch (SQLException e){
        }
        return bool;
    }

    public static int updateUser(Usuario usuario, boolean editUname) {
        int numFilas = 0;
        String sql = "UPDATE USERS.USUARIO SET UNAME = ?, PASSWD = ? WHERE ID = ?";
        String sqlUser = "RENAME USER ? TO ?";
        try(Connection con = conectarSignUp()){
            PreparedStatement sentencia = con.prepareStatement(sql);
            sentencia.setString(1, usuario.getUname());
            sentencia.setString(2, usuario.getPasswd());
            sentencia.setInt(3, usuario.getId());
            numFilas = sentencia.executeUpdate();
            if (editUname) {
                sentencia = con.prepareStatement(sqlUser);
                sentencia.setString(1, DataBaseManager.username);
                sentencia.setString(2, usuario.getUname());
                sentencia.executeUpdate();
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return numFilas;
    }
    public static int updateUserName(String old_uname, String new_uname){
        int numFilas=0;
        String sql = "RENAME USER " + old_uname + " TO " + new_uname;
        try(Connection con = conectarSignUp()){
            Statement sentencia = con.createStatement();
            numFilas = sentencia.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return numFilas;
    }
    public static int updatePasswd(Usuario usuario){
        int numFilas;
        String sql = "ALTER USER '" + usuario.getUname() + "'@'%' IDENTIFIED BY '" + usuario.getPasswd() + "'";
        try(Connection con = conectarSignUp()){
            Statement sentencia = con.createStatement();
            numFilas = sentencia.executeUpdate(sql);
        }catch (SQLException e){
            numFilas = 1;
            System.out.println(e.getMessage());
        }
        return numFilas;
    }
}
