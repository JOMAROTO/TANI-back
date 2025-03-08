package tani.dto.pedido;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InformacionPedidoDTO(
        @NotNull int idPedido,
        @NotNull int idUsuario,  // Relación con el usuario que hizo el pedido
        @NotNull LocalDateTime fechaPedido,
        @NotNull String estado
) {
}
