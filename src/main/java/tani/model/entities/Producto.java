package tani.model.entities;

import jakarta.persistence.*;
import lombok.*;
import tani.model.enums.TIPO_CALZADO;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_producto;

    private int id_calzado;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TIPO_CALZADO tipoCalzado;

    private String descripcion;

    private String talla;

    private int cantidad_displonible;

    private String imagen;

    private float precio;

    @OneToOne(mappedBy="producto")
    private Inventario inventario;

    @OneToMany(mappedBy="producto")
    private List<DetallePedido> detallePedidos;


}
