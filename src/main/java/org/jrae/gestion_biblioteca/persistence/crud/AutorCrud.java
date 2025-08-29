package org.jrae.gestion_biblioteca.persistence.crud;

import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorCrud extends JpaRepository<Autor, Integer> {
}