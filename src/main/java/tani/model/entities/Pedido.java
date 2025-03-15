package tani.model.entities;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import jakarta.persistence.*;
import tani.model.enums.ESTADO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_pedido;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime fecha_pedido;
    @Enumerated(EnumType.STRING)
    private ESTADO estado; // "Pendiente", "Pagado", "Cancelado"

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

}
