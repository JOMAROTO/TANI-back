package tani.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_pedido;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime fecha_pedido;

    private String estado;

    @OneToOne(mappedBy="pedido")
    private DetallePedido detallePedido;

}
