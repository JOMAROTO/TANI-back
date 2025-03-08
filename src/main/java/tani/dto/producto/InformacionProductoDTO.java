package tani.dto.producto;

import jakarta.validation.constraints.NotNull;
import tani.model.enums.TIPO_CALZADO;

public record InformacionProductoDTO(
        @NotNull int idProducto,
        @NotNull int idCalzado,
        @NotNull String nombre,
        @NotNull TIPO_CALZADO tipo_calzado,
        @NotNull String descripcion,
        @NotNull String talla,
        @NotNull int cantidadDisponible,
        @NotNull String imagen,
        @NotNull float precio
) {
}
