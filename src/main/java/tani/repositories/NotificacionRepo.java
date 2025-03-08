package tani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tani.model.entities.Inventario;
import tani.model.entities.Notificacion;

import java.util.List;

@Repository
public interface NotificacionRepo extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByInventario(Inventario inventario); // Obtener notificaciones de un inventario

}
