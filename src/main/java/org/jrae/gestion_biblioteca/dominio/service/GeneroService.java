package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.GeneroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class GeneroService implements IGeneroService {

    @Autowired
    private GeneroCrud crud;

    @Autowired
    private ILibroService libroService;

    @Override
    public List<Genero> listarGeneros() {
        return crud.findAll();
    }

    @Override
    public Genero buscarPorId(Integer id) {
        return crud.findById(id).orElse(null);
    }

    @Override
    public Genero buscarPorTipo(String tipo) {
        return crud.findByTipo(tipo).orElse(null);
    }

    @Override
    public List<Libro> listarLibrosPorGenero(String genero) {
        Genero generoObj = buscarPorTipo(genero);
        if (generoObj != null) {
            return libroService.buscarPorGenero(generoObj.getCodigoGenero());
        }
        return new ArrayList<>();
    }

    @Override
    public void guardarGenero(Genero genero) {
        crud.save(genero);
    }

    @Override
    public void eliminarGenero(Genero genero) {
        crud.delete(genero);
    }
}