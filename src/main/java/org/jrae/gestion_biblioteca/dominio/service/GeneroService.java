package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.GeneroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GeneroService implements IGeneroService{
    @Autowired
    private GeneroCrud crud;

    @Override
    public List<Genero> listarGeneros() {
        List<Genero> generos = crud.findAll();
        return generos;
    }

    @Override
    public Genero listarPorId(Integer codigo) {
        Genero genero = crud.findById(codigo).orElse(null);
        return genero;
    }

    @Override
    public Genero listarPorGenero(String genero) {
        for (Genero g : listarGeneros()){
            if (genero.equalsIgnoreCase(g.getTipo())){

            }
        }
        return null;
    }

    @Override
    public void guardarGenero(Genero genero) {

    }

    @Override
    public void eliminarGenero(Genero genero) {

    }
}
