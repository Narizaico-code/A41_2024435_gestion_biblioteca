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