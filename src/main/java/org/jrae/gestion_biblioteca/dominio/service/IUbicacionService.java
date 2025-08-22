package org.jrae.gestion_biblioteca.dominio.service;

import org.jrae.gestion_biblioteca.persistence.entity.Ubicacion;

import java.util.List;

public interface IUbicacionService {
    List<Ubicacion> listarUbicaciones();
    Ubicacion buscarUbicacionPorId(Integer codigo);
    Ubicacion buscarUbicacion(String ubicacion);
    void guardarUbicacion(Ubicacion ubicacion);
    void eliminarCliente(Ubicacion ubicacion);
}
