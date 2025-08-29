package org.jrae.gestion_biblioteca.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer codigoAutor;

    private String nombre;
    private String nacionalidad;
}