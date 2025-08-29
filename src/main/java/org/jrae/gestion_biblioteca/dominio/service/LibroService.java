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
    public void guardarLibro(Libro libro) {
        crud.save(libro);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        crud.delete(libro);
    }
}