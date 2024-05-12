USE USERS;

CREATE TABLE USUARIO(
        ID int PRIMARY KEY AUTO_INCREMENT,
        UNAME varchar(30) NOT NULL,
        PASSWD varchar(30) NOT NULL,
        DB varchar(40)
);