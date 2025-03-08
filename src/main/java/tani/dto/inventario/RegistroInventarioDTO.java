package tani.dto.inventario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegistroInventarioDTO(
        @NotNull int idProducto,  // Se usa el identificador del producto
        @NotNull int cantidad,
        @NotNull LocalDate ultimaActualizacion
) {
}

