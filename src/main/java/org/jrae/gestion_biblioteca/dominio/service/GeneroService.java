package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.GeneroCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
@Service
public class GeneroService implements IGeneroService{
    @Autowired
    private GeneroCrud crud;
    @Autowired
    private ILibroService libroService;

    private static final Logger logger = LoggerFactory.getLogger(GeneroService.class);
    // Crear un objeto String para saltos de linea que no los maneja el logger
    String sl = System.lineSeparator();

    @Override
    public List<Genero> listarGeneros() {
        List<Genero> generos = crud.findAll();
        generos.forEach(genero -> logger.info(genero.toString() + sl));
        return generos;
    }

    @Override
    public Genero listarPorId(Integer codigo) {
        Genero genero = crud.findById(codigo).orElse(null);
        if (genero != null){
            logger.info("Genero encontrado: " + genero + sl);
        }else {
            logger.info("Genero con codigo " + codigo + " no encontrado: " + genero + sl);
        }
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
