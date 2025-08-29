package org.jrae.gestion_biblioteca.persistence.crud;

import org.jrae.gestion_biblioteca.persistence.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionCrud extends JpaRepository<Ubicacion, Integer> {
}