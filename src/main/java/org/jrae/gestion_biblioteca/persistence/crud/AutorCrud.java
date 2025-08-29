package org.jrae.gestion_biblioteca.persistence.crud;

import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AutorCrud extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByNombre(String nombre);
}