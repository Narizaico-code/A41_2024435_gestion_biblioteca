package org.jrae.gestion_biblioteca.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Ubicaciones")
// Lombok
@Data //Genera los getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoUbicacion; //Permite usar null en vez de 0
    @Column
    private String edificio;
    private String salon;
    private String estanteria;
    private Integer fila;
}
