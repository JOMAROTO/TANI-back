package tani.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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

    @OneToOne
    private Producto producto;

    private int cantidad;

    private LocalDate ultima_actualizacion;

    @OneToMany(mappedBy="inventario")
    private List<Notificacion> notificaciones;


}
