package tani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tani.model.entities.Producto;
import tani.model.enums.TIPO_CALZADO;
import java.util.List;
import java.util.Optional;

public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByTipoCalzado(TIPO_CALZADO tipoCalzado);
}
