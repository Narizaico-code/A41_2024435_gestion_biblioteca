package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.AutorCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class AutorService implements IAutorService {

    @Autowired
    private AutorCrud crud;

    @Autowired
    private ILibroService libroService;

    @Override
    public List<Autor> listarAutores() {
        return crud.findAll();
    }

    @Override
    public Autor buscarPorId(Integer id) {
        return crud.findById(id).orElse(null);
    }

    @Override
    public Autor buscarPorNombre(String nombre) {
        return crud.findByNombre(nombre).orElse(null);
    }

    @Override
    public List<Libro> listarLibrosPorAutor(String autor) {
        Autor autorObj = buscarPorNombre(autor);
        if (autorObj != null) {
            return libroService.buscarPorAutor(autorObj.getCodigoAutor());
        }
        return new ArrayList<>();
    }

    @Override
    public void guardarAutor(Autor autor) {
        crud.save(autor);
    }

    @Override
    public void eliminarAutor(Autor autor) {
        crud.delete(autor);
    }
}