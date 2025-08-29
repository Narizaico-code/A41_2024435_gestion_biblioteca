package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.entity.Ubicacion;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import java.util.List;

public interface IUbicacionService {
    List<Ubicacion> listarUbicaciones();
    Ubicacion buscarPorId(Integer id);
    List<Libro> listarLibrosPorUbicacion(Integer codigoUbicacion);
    void guardarUbicacion(Ubicacion ubicacion);
    void eliminarUbicacion(Ubicacion ubicacion);
}