package tani.services.interfaces;

import tani.dto.producto.InformacionProductoDTO;
import tani.dto.productotalla.RegistroProductoTallaDTO;
import tani.dto.productotalla.InformacionProductoTallaDTO;
import tani.dto.productotalla.EditarProductoTallaDTO;

import java.util.List;

public interface ProductoTallaServicio {
    void crearProductoTallas(List<RegistroProductoTallaDTO> tallas, InformacionProductoDTO productoDTO);
    InformacionProductoTallaDTO obtenerProductoTallaPorId(int id);
    List<InformacionProductoTallaDTO> listarProductoTallas(int productoId);
    InformacionProductoTallaDTO editarProductoTalla(EditarProductoTallaDTO productoTallaDTO);
    void eliminarProductoTalla(int id);
    List<RegistroProductoTallaDTO> obtenerTallasPorProductoId(int productoId);

}