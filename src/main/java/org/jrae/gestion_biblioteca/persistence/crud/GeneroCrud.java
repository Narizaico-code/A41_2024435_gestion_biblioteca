package org.jrae.gestion_biblioteca.persistence.crud;

import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GeneroCrud extends JpaRepository<Genero, Integer>{
}
