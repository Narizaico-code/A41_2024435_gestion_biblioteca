package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.crud.UbicacionCrud;
import org.jrae.gestion_biblioteca.persistence.entity.Ubicacion;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class UbicacionService implements IUbicacionService {

    @Autowired
    private UbicacionCrud crud;

    @Autowired
    private ILibroService libroService;

    @Override
    public List<Ubicacion> listarUbicaciones() {
        return crud.findAll();
    }

    @Override
    public Ubicacion buscarPorId(Integer id) {
        return crud.findById(id).orElse(null);
    }

    @Override
    public List<Libro> listarLibrosPorUbicacion(Integer codigoUbicacion) {
        return libroService.buscarPorUbicacion(codigoUbicacion);
    }

    @Override
    public void guardarUbicacion(Ubicacion ubicacion) {
        crud.save(ubicacion);
    }

    @Override
    public void eliminarUbicacion(Ubicacion ubicacion) {
        crud.delete(ubicacion);
    }
}