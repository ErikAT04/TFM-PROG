INSERT INTO CLIENTE(dni, nombre, apellidos, telf, edad, fnac, especificaciones) VALUES('12123312B', 'Marcos', 'Gabriel Fernandez', 983111772, TIMESTAMPDIFF(YEAR, '2001-02-04', SYSDATE()), str_to_date('04-02-2001', '%d-%m-%Y'),'Mala postura repetida');
INSERT INTO CLIENTE(dni, nombre, apellidos, telf, edad, fnac, especificaciones) VALUES('12121312B', 'Marcos', 'SI', 650777531, TIMESTAMPDIFF(YEAR, '2005-01-11', SYSDATE()), str_to_date('2005-01-11', '%Y-%m-%d'),'Pie de atleta');
INSERT INTO CLIENTE(dni, nombre, apellidos, telf, edad, fnac) VALUES('12244221J', 'Marcos', 'SI', 650777531, TIMESTAMPDIFF(YEAR, '2005-01-11', SYSDATE()), str_to_date('1998-07-02', '%Y-%m-%d'));
INSERT INTO PRODUCTO(nombre, marca, stock) VALUES('Pintalabios Fructis', 'Fructis', 5);
INSERT INTO PRODUCTO(nombre, marca, stock) VALUES('Crema depilatoria X','HyS', 10);
INSERT INTO PRODUCTO(nombre, marca, stock) VALUES('Pintalabios fresabrosa','Condition', 15);
INSERT INTO PRODUCTO(nombre, marca, stock) VALUES('Crema hidratante de chocolate','ChocoBeauty', 20);
INSERT INTO PRODUCTO(nombre, marca, stock) VALUES('Prueba','Es solo de prueba', 11);
INSERT INTO PRODUCTO(nombre, marca, stock) VALUES('Crema de Cacao','MagicRepair', 9);
INSERT INTO TRATAMIENTO(nombre, descripcion, precio, duracion_media_horas) values('Depilación láser', 'Para acabar con los vellos no deseados en un par de sesiones', 35, 1);
INSERT INTO TRATAMIENTO(nombre, descripcion, precio, duracion_media_horas) values('Tratamiento facial', 'Eliminar grasa facial, granos, etc.', 19.5, 1.5);
INSERT INTO TRATAMIENTO(nombre, descripcion, precio, duracion_media_horas) values('Manicura semi', 'Aplicación de esmalte semipermanente con decoración en las uñas', 20, 1.5);
INSERT INTO TRATAMIENTO(nombre, descripcion, precio, duracion_media_horas) values('Depilación manual', 'Depilación con cera o crema', 22, 0.5);
INSERT INTO PRODUCTO_TRATAMIENTO VALUES(2, 4);
INSERT INTO PRODUCTO_TRATAMIENTO VALUES(1, 1);
INSERT INTO PRODUCTO_TRATAMIENTO VALUES(1, 3);
INSERT INTO PRODUCTO_TRATAMIENTO VALUES(4, 3);
INSERT INTO TRABAJADOR(dni, nombre, apellidos, horario) VALUES ('71121212B', 'Javier', 'Ramos Miguel', 'Lunes, Martes, Miércoles, Viernes');
INSERT INTO TRABAJADOR(dni, nombre, apellidos, horario) VALUES ('81222110C', 'Manuel', 'Carrasco Medina', 'Lunes, Martes, Miércoles, Jueves');
INSERT INTO TRABAJADOR(dni, nombre, apellidos, horario) VALUES ('12122211G', 'Laura', 'Ramirez Casco', 'Sábado');
INSERT INTO CITA(fecha_hora, observaciones, tratamiento, trabajadorEncargado, clientePedido) VALUES(str_to_date('2024-06-13-11:50', '%Y-%m-%d-%H:%i'), 'Cliente que quizá necesita atencion de dos trabajadores', 1, 1, '12123312B');