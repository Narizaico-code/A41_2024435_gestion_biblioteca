package org.jrae.gestion_biblioteca.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer codigoLibro;
    private Integer codigoGenero;
    private Integer codigoUbicacion;
    private Integer codigoAutor;
    private String titulo;
    private LocalDate fechaPublicacion;
    private Integer cantidad;
    private String disponibilidad;
}