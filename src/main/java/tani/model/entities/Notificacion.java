package tani.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Notificacion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_notificacion;

    @ManyToOne
    private Inventario inventario;

    private String mensaje;

    private LocalDateTime fecha;
}
