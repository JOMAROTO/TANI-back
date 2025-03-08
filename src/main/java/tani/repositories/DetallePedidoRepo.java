package tani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tani.dto.pedido.InformacionPedidoDTO;
import tani.model.entities.DetallePedido;
import tani.model.entities.Pedido;

import java.util.List;

@Repository
public interface DetallePedidoRepo extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedido(Pedido pedido); // Obtener detalles de un pedido

}
