package tani.model.entities;
import jakarta.persistence.*;
import lombok.*;
import tani.model.enums.TIPO_USUARIO;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_usuario;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String correo;
    private String contrasenia;
    @Enumerated(EnumType.STRING)
    private TIPO_USUARIO tipoUsuario;

}


