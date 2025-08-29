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

    @ManyToOne
    @JoinColumn(name = "codigo_genero")
    private Genero genero;
    @ManyToOne
    @JoinColumn(name = "codigo_ubicacion")
    private Ubicacion ubicacion;
    @ManyToOne
    @JoinColumn(name = "codigo_autor")
    private Autor autor;

    @Column
    private String titulo;
    private LocalDate fechaPublicacion;
    private Integer cantidad;
    private String disponibilidad;
}