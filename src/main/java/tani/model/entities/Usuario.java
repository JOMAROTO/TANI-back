package tani.model.entities;

import jakarta.persistence.*;
import lombok.*;
import tani.model.enums.TIPO_USUARIO;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    private int id_usuario;

    private String nombre;

    private LocalDate fechaNacimiento;

    private String telefono;

    private String correo;

    private String contraseña;

    @Enumerated(EnumType.STRING)
    private TIPO_USUARIO tipoUsuario;

    @OneToMany(mappedBy="usuario")
    private List<Pedido> pedidos;

}


