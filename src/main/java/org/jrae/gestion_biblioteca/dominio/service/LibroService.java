package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.GeneroCrud;
import org.jrae.gestion_biblioteca.persistence.crud.LibroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibroService implements ILibroService{
    @Autowired
    private LibroCrud crud;
    private static final Logger logger = LoggerFactory.getLogger(GeneroService.class);
    String sl = System.lineSeparator();

    @Override
    public List<Libro> listarLibros() {
        List<Libro> libros = crud.findAll();
        libros.forEach(genero -> logger.info(genero.toString() + sl));
        return libros;
    }

    @Override
    public Libro buscarLibroFiltro(String filtro) {

        return null;
    }

    @Override
    public Libro buscarLibroPorId(Integer codigo) {
        return null;
    }

    @Override
    public void guardarLibro(Libro libro) {

    }

    @Override
    public void eliminarLibro(Libro libro) {

    }

    @Override
    public Libro buscarLibroCategoria() {
        return null;
    }
}
