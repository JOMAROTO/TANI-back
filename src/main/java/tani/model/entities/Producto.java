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
public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_producto;

    private int id_calzado;

    private String nombre;

    private String tipo_calzado;

    private String descripcion;

    private String talla;

    private int cantidad_displonible;

    private String imagen;

    private float precio;

}

enum TIPO_CALZADO{
    BOTA,
    TACON,
    TENIS,
    ZAPATO,
    ZANDALIA
}