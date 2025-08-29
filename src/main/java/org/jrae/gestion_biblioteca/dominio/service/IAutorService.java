package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import java.util.List;

public interface IAutorService {
    List<Autor> listarAutores();
    Autor buscarPorId(Integer id);
    Autor buscarPorNombre(String nombre);
    List<Libro> listarLibrosPorAutor(String autor);
    void guardarAutor(Autor autor);
    void eliminarAutor(Autor autor);
}