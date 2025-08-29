package org.jrae.gestion_biblioteca.persistence.crud;


import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroCrud extends JpaRepository<Libro, Integer> {
}