package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.LibroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService implements ILibroService {

    @Autowired
    private LibroCrud crud;

    @Override
    public List<Libro> listarLibros() {
        return crud.findAll();
    }

    @Override
    public Libro buscarPorId(Integer id) {
        return crud.findById(id).orElse(null);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        return crud.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<Libro> buscarPorGenero(Integer codigoGenero) {
        return crud.findByGenero_CodigoGenero(codigoGenero);
    }

    @Override
    public List<Libro> buscarPorAutor(Integer codigoAutor) {
        return crud.findByAutor_CodigoAutor(codigoAutor);
    }

    @Override
    public List<Libro> buscarPorUbicacion(Integer codigoUbicacion) {
        return crud.findByUbicacion_CodigoUbicacion(codigoUbicacion);
    }

    @Override
    public void guardarLibro(Libro libro) {
        crud.save(libro);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        crud.delete(libro);
    }
}