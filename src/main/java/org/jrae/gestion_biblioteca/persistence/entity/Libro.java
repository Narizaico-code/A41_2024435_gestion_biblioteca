package org.jrae.gestion_biblioteca.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Libros")
// Lombok
@Data //Genera los getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoLibro; //Permite usar null en vez de 0
    @Column
    private Integer codigoGenero;
    private Integer codigoUbicacion;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private Integer cantidad;
    private String disponibilidad;
}