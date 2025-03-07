package tani.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleProducto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_detalle;

    private int id_pedido;

    private int id_producto;

    private int cantidad;

    private float precio_unitario;

    private float subtotal;

}