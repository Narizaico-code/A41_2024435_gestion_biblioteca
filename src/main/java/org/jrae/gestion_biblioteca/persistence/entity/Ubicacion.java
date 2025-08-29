package org.jrae.gestion_biblioteca.persistence.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Ubicaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoUbicacion;

    @Column
    private String edificio;
    private String salon;
    private String estanteria;
    private Integer fila;

    public String getDescripcionCompleta() {
        return "Edificio: " + edificio + ", Salon: " + salon + ", Estanteria: " + estanteria + ", Fila: " + fila;
    }
}