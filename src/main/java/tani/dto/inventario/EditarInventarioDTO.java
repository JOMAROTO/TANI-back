package tani.dto.inventario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EditarInventarioDTO(
        @NotNull int idInventario,//llave primaria
        @NotNull int cantidad,//cantidad de producto
        @NotNull LocalDate ultimaActualizacion//fecha de la ultima actualizacion,

) {
}
