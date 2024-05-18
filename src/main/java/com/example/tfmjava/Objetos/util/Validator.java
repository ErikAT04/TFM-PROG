package com.example.tfmjava.Objetos.util;

import javafx.scene.control.Alert;

public class Validator {
    public static boolean validarDNI(String dni){
        if (dni.matches("(^[0-9]{8}[A-Z]$)")){
            int dniSinLetra = (Integer.parseInt(dni.substring(0, 8)));
            String match = switch (dniSinLetra%23){
                case 1 -> "R";
                case 2 -> "W";
                case 3 -> "A";
                case 4 -> "G";
                case 5 -> "M";
                case 6 -> "Y";
                case 7 -> "F";
                case 8 -> "P";
                case 9 -> "D";
                case 10 -> "X";
                case 11 -> "B";
                case 12 -> "N";
                case 13 -> "J";
                case 14 -> "Z";
                case 15 -> "S";
                case 16 -> "Q";
                case 17 -> "V";
                case 18 -> "H";
                case 19 -> "L";
                case 20 -> "C";
                case 21 -> "K";
                case 22 -> "E";
                default -> "T";
            };
            return dni.contains(match);
        } else {
            return false;
        }
    }
    public static boolean validarUname(String uname){
        if (uname.contains(" ")){
            return false;
        } else {
            return uname.length() >= 3 && uname.length() <= 15;
        }
    }
    public static boolean validarPasswd(String pass){
        if (pass.length() < 8 || pass.length() > 30){
            return false;
        } else {
            return !(pass.matches("[;,*`^]"));
        }
    }
    public static void mostrarInfoUname(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de Nombre");
        alert.setHeaderText("Normas del nombre de usuario:");
        alert.setContentText("""
                1. El nombre tiene que tener entre 4 y 30 caracteres
                2. El nombre no puede tener espacios ni ";" en su interior
                3. Solo hay un nombre de usuario disponible por base de datos.""");
        alert.showAndWait();
    }

    public static void mostrarInfoPasswd() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información de la contraseña");
        alert.setHeaderText("Normas de la contraseña:");
        alert.setContentText("""
                1. Tiene que tener entre 8 y 30 caracteres
                2. No puede tener espacios ni los siguientes caracteres ";, ^, ;, *,`" en su interior
                3. Solo hay un nombre de usuario disponible por base de datos.""");
        alert.showAndWait();
    }
}
