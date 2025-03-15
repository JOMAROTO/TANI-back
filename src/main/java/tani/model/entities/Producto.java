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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_producto;

    private String nombre;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TIPO_CALZADO tipoCalzado;

    private String imagen;
    private float precio;

    @OneToMany(mappedBy = "producto")
    private List<ProductoTalla> tallas;


}
