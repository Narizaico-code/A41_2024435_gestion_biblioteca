package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.entity.Libro;

import java.util.List;

public interface ILibroService {
    List<Libro> listarLibros();
    Libro buscarLibroFiltro(String filtro);
    Libro buscarLibroPorId(Integer codigo);
    void guardarLibro(Libro libro);
    void eliminarLibro(Libro libro);
}
