package tani.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tani.model.enums.TIPO_CALZADO;

public record RegistroProductoDTO(
        @NotNull int idCalzado,
        @NotNull  String nombre,
        @NotNull    TIPO_CALZADO tipo_calzado,
        @NotNull   String descripcion,
        @NotNull    String talla,
        @NotNull   int cantidadDisponible,
        @NotBlank String imagen,
        @NotNull     float precio
) {
}
