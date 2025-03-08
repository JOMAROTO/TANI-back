package tani.dto.pedido;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroPedidoDTO(
        @NotNull int idUsuario,  // Se usa el identificador del usuario
        @NotNull LocalDateTime fechaPedido,
        @NotNull String estado
) {
}
