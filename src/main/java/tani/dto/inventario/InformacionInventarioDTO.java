package tani.dto.inventario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InformacionInventarioDTO(
        @NotNull int idDetalle,
        @NotNull  int idProducto,
        @NotNull int cantidad,
        @NotNull LocalDate ultimaActualizacion
) {
}
