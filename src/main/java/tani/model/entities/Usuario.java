package tani.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Tipo_Usuario tipo_usuario;


}

enum Tipo_Usuario{
    USUARIO,
    ADMINISTRADOR
}
