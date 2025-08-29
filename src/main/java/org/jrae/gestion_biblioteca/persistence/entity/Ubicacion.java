package org.jrae.gestion_biblioteca.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ubicaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer codigoUbicacion;
    private String edificio;
    private String salon;
    private String estanteria;
    private Integer fila;

    public String getDescripcionCompleta() {
        return edificio + " - " + salon + " - Est: " + estanteria + " - Fila: " + fila;
    }
}