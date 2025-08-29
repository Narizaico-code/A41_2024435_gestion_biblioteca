package org.jrae.gestion_biblioteca.persistence.crud;

import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GeneroCrud extends JpaRepository<Genero, Integer>{
    Optional<Genero> findByTipo(String tipo);
}
