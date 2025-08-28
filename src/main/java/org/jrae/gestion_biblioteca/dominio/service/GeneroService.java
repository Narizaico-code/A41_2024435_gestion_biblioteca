package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.GeneroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeneroService implements IGeneroService {
    @Autowired
    private GeneroCrud crud;
    @Autowired
    private ILibroService libroService;

    private static final Logger logger = LoggerFactory.getLogger(GeneroService.class);
    String sl = System.lineSeparator();

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
    public List<Libro> listarPorGenero(String genero) {
        List<Libro> librosPorGenero = new ArrayList<>();
        for (Genero g : listarGeneros()) {
            if (genero.equalsIgnoreCase(g.getTipo())) {
                for (Libro l : libroService.listarLibros()) {
                    if (g.getCodigoGenero() == l.getCodigoGenero()) {
                        librosPorGenero.add(l);
                    }
                }
            }
        }
        return librosPorGenero;
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
