package tani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tani.model.entities.Inventario;
import tani.model.entities.Producto;

import java.util.Optional;

@Repository
public interface InventarioRepo extends JpaRepository<Inventario, Integer> {
    Optional<Inventario> findByProducto(Producto producto); // Buscar inventario por producto

}
