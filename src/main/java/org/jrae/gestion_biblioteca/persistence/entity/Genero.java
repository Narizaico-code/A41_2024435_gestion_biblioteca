package org.jrae.gestion_biblioteca.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Generos")
// Lombok
@Data //Genera los getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoGenero; //Permite usar null en vez de 0
    @Column
    private String tipo;
    private String descripcion;
}
