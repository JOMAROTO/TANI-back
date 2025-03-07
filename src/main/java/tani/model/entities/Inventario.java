package tani.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inventario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_detalle;

    private int id_producto;

    private int cantidad;

    private LocalDate ultima_actualizacion;

}
