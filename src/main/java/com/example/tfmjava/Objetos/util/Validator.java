package com.example.tfmjava.Objetos.util;

public class Validator {
    public static boolean validarDNI(String dni){
        if (dni.matches("(^[0-9]{8}[A-Z]$)")){
            int dniSinLetra = (Integer.parseInt(dni.substring(0, 7)));
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
}
