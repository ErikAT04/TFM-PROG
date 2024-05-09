package com.example.tfmjava.Objetos.LogInSignUp;

import com.example.tfmjava.Objetos.util.Resultado;

import java.sql.*;

public class UsuarioDAO {
    public static Connection conectarLogin(){
        Connection con=null;
        String usuario = "root";
        String password = "IESRibera23";
        String url = "jdbc:mysql://localhost:3306/users";
        try {
            con = DriverManager.getConnection(url, usuario, password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static int register(Usuario usuario){
        String sqlInsert = "INSERT INTO USUARIO(ID, UNAME, PASSWD, DB) VALUES (?, ?, ?, ?)";
        int numFilas;
        try{
            Connection con = conectarLogin();

            PreparedStatement sentencia = con.prepareStatement(sqlInsert);
            sentencia.setInt(1, usuario.getId());
            sentencia.setString(2, usuario.getUname());
            sentencia.setString(3, usuario.getPasswd());
            sentencia.setString(4, usuario.getDb());

            numFilas = sentencia.executeUpdate();

            con.close();
        }catch (SQLException e){
            return 0;
        }
        return numFilas;
    }
    public static Usuario checkForLogin(String uname, String passwd){
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
                usuario = new Usuario(uname, passwd, dB, id); //El usuario y la contraseña ya los tengo de antes si la consulta es correcta, sería una tontería volver a crear variables
            }

            con.close();

        }catch (SQLException e){
        }
        return  usuario;
    }
    public static void eraseUser(Usuario usuario){
        String sqlDeleteDB = "DROP DATABASE " + usuario.getDb();
        String sqlDeleteUser = "DROP USER " + usuario.getUname();

        try {
            Connection con = conectarLogin();
            PreparedStatement sentencia = con.prepareStatement(sqlDeleteDB);
            sentencia.executeUpdate();
            sentencia = con.prepareStatement(sqlDeleteUser);
            sentencia.executeUpdate();
            con.close();
        }catch (SQLException e){

        }
    }
}
