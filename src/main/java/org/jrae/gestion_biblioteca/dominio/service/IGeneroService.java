package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import java.util.List;

public interface IGeneroService {
    List<Genero> listarGeneros();
    Genero buscarPorId(Integer id);
    Genero buscarPorTipo(String tipo);
    List<Libro> listarLibrosPorGenero(String genero);
    void guardarGenero(Genero genero);
    void eliminarGenero(Genero genero);
}