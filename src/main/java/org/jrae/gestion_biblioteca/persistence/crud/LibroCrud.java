package org.jrae.gestion_biblioteca.persistence.crud;

import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroCrud extends JpaRepository<Libro, Integer> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByGenero_CodigoGenero(Integer codigoGenero);
    List<Libro> findByAutor_CodigoAutor(Integer codigoAutor);
    List<Libro> findByUbicacion_CodigoUbicacion(Integer codigoUbicacion);
}