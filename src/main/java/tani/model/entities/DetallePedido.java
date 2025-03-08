package tani.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetallePedido implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_detalle;

    @OneToOne
    private Pedido pedido;

    @ManyToOne
    private Producto producto;

    private int cantidad;

    private float precio_unitario;

    private float subtotal;

}