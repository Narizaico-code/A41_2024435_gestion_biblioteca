package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.entity.Genero;

import java.util.List;

public interface IGeneroService {
    List<Genero> listarGeneros();
    Genero listarPorId(Integer codigo);
    Genero listarPorGenero(String genero);
    void guardarGenero(Genero genero);
    void eliminarGenero(Genero genero);
}