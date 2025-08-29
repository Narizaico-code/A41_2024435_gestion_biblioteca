drop database if exists gestion_biblioteca_db;
create database gestion_biblioteca_db;
use gestion_biblioteca_db;
create table Generos(
	codigo_genero integer auto_increment,
    tipo varchar(255),
    descripcion varchar(255),
    constraint pk_genero primary key (codigo_genero)
);
create table Ubicaciones(
	codigo_ubicacion integer auto_increment,
    edificio varchar(16),
    salon varchar(16),
    estanteria varchar(2),
    fila integer,
    constraint pk_ubicacion primary key (codigo_ubicacion)
);
create table Autores(
    codigo_autor integer auto_increment,
    nombre varchar(255),
    nacionalidad varchar(255),
    constraint pk_autor primary key (codigo_autor)
);
create table Libros(
	codigo_libro integer auto_increment,
    codigo_genero integer,
    codigo_ubicacion integer,
    codigo_autor integer,
    titulo varchar(128),
    fecha_publicacion date,
    cantidad integer,
    disponibilidad enum('disponible', 'no disponible') default 'disponible',
    constraint pk_libro primary key (codigo_libro),
    constraint fk_libro_genero foreign key (codigo_genero)
		references Generos(codigo_genero) on delete cascade,
	constraint fk_libro_ubicacion foreign key (codigo_ubicacion)
		references Ubicaciones(codigo_ubicacion) on delete cascade,
	constraint fk_libro_autor foreign key (codigo_autor)
	    references Autores(codigo_autor) on delete cascade
);
-- Generos
INSERT INTO Generos (tipo, descripcion) VALUES
('Novela', 'Un relato de ficción en prosa, generalmente largo.'),
('Cuento', 'Una narración breve de ficción.'),
('Realismo Mágico', 'Un género literario donde lo mágico se presenta como algo normal.'),
('Poesía', 'Composiciones literarias que expresan emociones o ideas.'),
('Fábulas', 'Relatos breves que tienen una moraleja.');

-- Ubicaciones
INSERT INTO Ubicaciones (edificio, salon, estanteria, fila) VALUES
('A', '101', 'N', 5), -- Novelas
('A', '102', 'C', 3), -- Cuentos
('B', '203', 'M', 1), -- Realismo Mágico
('B', '204', 'P', 7), -- Poesía
('C', '305', 'F', 2); -- Fábulas

-- Autores
INSERT INTO Autores (nombre, nacionalidad) VALUES
('Miguel Ángel Asturias', 'Guatemalteca'),
('Augusto Monterroso', 'Guatemalteca'),
('Rodrigo Rey Rosa', 'Guatemalteca'),
('Luis de Lion', 'Guatemalteca'),
('Mario Roberto Morales', 'Guatemalteca');
-- Libros de Miguel Ángel Asturias
INSERT INTO Libros (codigo_genero, codigo_ubicacion, codigo_autor, titulo, fecha_publicacion, cantidad) VALUES
(1, 1, 1, 'El Señor Presidente', '1946-01-01', 5),
(3, 3, 1, 'Hombres de maíz', '1949-01-01', 3),
(1, 1, 1, 'Viento Fuerte', '1950-01-01', 2),
(2, 2, 1, 'Leyendas de Guatemala', '1930-01-01', 4);

-- Libros de Augusto Monterroso
INSERT INTO Libros (codigo_genero, codigo_ubicacion, codigo_autor, titulo, fecha_publicacion, cantidad) VALUES
(5, 5, 2, 'La oveja negra y demás fábulas', '1969-01-01', 6),
(2, 2, 2, 'Movimiento perpetuo', '1972-01-01', 3),
(1, 1, 2, 'Lo demás es silencio (La vida y obra de Eduardo Torres)', '1978-01-01', 2),
(2, 2, 2, 'El dinosaurio', '1959-01-01', 5);

-- Libros de Rodrigo Rey Rosa
INSERT INTO Libros (codigo_genero, codigo_ubicacion, codigo_autor, titulo, fecha_publicacion, cantidad) VALUES
(1, 1, 3, 'El Cojo Ilustrado', '2009-01-01', 3),
(1, 1, 3, 'El libro de las catacumbas', '2000-01-01', 4),
(2, 2, 3, 'Cárcel de árboles', '1991-01-01', 2),
(1, 1, 3, 'El cartógrafo', '2017-01-01', 5);

-- Libros de Luis de Lion
INSERT INTO Libros (codigo_genero, codigo_ubicacion, codigo_autor, titulo, fecha_publicacion, cantidad) VALUES
(1, 1, 4, 'El tiempo principia en Xibalbá', '1975-01-01', 4),
(4, 4, 4, 'Poemas del espejo', '1983-01-01', 2),
(2, 2, 4, 'La otra cara de la moneda', '1984-01-01', 3),
(2, 2, 4, 'Los signos de la vida', '1985-01-01', 4);

-- Libros de Mario Roberto Morales
INSERT INTO Libros (codigo_genero, codigo_ubicacion, codigo_autor, titulo, fecha_publicacion, cantidad) VALUES
(1, 1, 5, 'Señores bajo los árboles', '1994-01-01', 3),
(1, 1, 5, 'El esplendor de la pirámide', '1987-01-01', 2),
(1, 1, 5, 'La mujer de los ojos de lluvia', '2009-01-01', 4),
(2, 2, 5, 'Los que se fueron del alma', '2008-01-01', 5);