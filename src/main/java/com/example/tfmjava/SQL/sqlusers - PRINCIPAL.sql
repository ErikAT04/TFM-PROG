/*Este es el código que hay que recorrer antes que ningún otro, el principal por así decirlo*/
CREATE DATABASE USERS;
USE USERS;

CREATE TABLE USUARIO(
        ID int PRIMARY KEY AUTO_INCREMENT,
        UNAME varchar(30) NOT NULL,
        PASSWD varchar(30) NOT NULL,
        DB varchar(40)
);

/*Los otros son:
    - SQLMain: Contiene TODA la plantilla de cada base de datos
    - DummySQL1: Para generar un modelo de pruebas
    - DummySQL2: Para insertar en la base de datos del modelo de pruebas las tablas convenientes
*/