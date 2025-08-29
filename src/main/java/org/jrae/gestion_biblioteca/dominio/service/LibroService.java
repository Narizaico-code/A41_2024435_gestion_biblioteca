package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.LibroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

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
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : listarLibros()) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    @Override
    public List<Libro> buscarPorGenero(Integer codigoGenero) {
        List<Libro> librosPorGenero = new ArrayList<>();
        for (Libro libro : listarLibros()) {
            if (libro.getCodigoGenero() != null && libro.getCodigoGenero().equals(codigoGenero)) {
                librosPorGenero.add(libro);
            }
        }
        return librosPorGenero;
    }

    @Override
    public List<Libro> buscarPorAutor(Integer codigoAutor) {
        List<Libro> librosPorAutor = new ArrayList<>();
        for (Libro libro : listarLibros()) {
            if (libro.getCodigoAutor() != null && libro.getCodigoAutor().equals(codigoAutor)) {
                librosPorAutor.add(libro);
            }
        }
        return librosPorAutor;
    }

    @Override
    public List<Libro> buscarPorUbicacion(Integer codigoUbicacion) {
        List<Libro> librosPorUbicacion = new ArrayList<>();
        for (Libro libro : listarLibros()) {
            if (libro.getCodigoUbicacion() != null && libro.getCodigoUbicacion().equals(codigoUbicacion)) {
                librosPorUbicacion.add(libro);
            }
        }
        return librosPorUbicacion;
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