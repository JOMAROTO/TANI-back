package tani.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tani.dto.producto.InformacionProductoDTO;
import tani.dto.productotalla.RegistroProductoTallaDTO;
import tani.dto.productotalla.InformacionProductoTallaDTO;
import tani.dto.productotalla.EditarProductoTallaDTO;
import tani.model.entities.Producto;
import tani.model.entities.ProductoTalla;
import tani.repositories.ProductoRepo;
import tani.repositories.ProductoTallaRepo;
import tani.services.interfaces.ProductoTallaServicio;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoTallaServicioImpl implements ProductoTallaServicio {

    private final ProductoTallaRepo productoTallaRepo;
    private final ProductoRepo productoRepo;

    @Override
    public void crearProductoTallas(List<RegistroProductoTallaDTO> tallas, InformacionProductoDTO productoDTO) {
        Producto producto = productoRepo.findById(productoDTO.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        for (RegistroProductoTallaDTO tallaDTO : tallas) {
            ProductoTalla productoTalla = new ProductoTalla();
            productoTalla.setProducto(producto);
            productoTalla.setTalla(tallaDTO.talla());
            productoTalla.setCantidad(tallaDTO.cantidad());
            productoTallaRepo.save(productoTalla);
        }
    }

    @Override
    public InformacionProductoTallaDTO obtenerProductoTallaPorId(int id) {
        ProductoTalla productoTalla = productoTallaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductoTalla no encontrado"));
        return new InformacionProductoTallaDTO(
                productoTalla.getId(),
                productoTalla.getProducto().getId_producto(),
                productoTalla.getTalla(),
                productoTalla.getCantidad()
        );
    }

    @Override
    public List<InformacionProductoTallaDTO> listarProductoTallas(int productoId) {
        return productoTallaRepo.findAll().stream()
                .filter(productoTalla -> productoTalla.getProducto().getId_producto() == productoId)
                .map(productoTalla -> new InformacionProductoTallaDTO(
                        productoTalla.getId(),
                        productoTalla.getProducto().getId_producto(),
                        productoTalla.getTalla(),
                        productoTalla.getCantidad()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public InformacionProductoTallaDTO editarProductoTalla(EditarProductoTallaDTO productoTallaDTO) {
        ProductoTalla productoTalla = productoTallaRepo.findById(productoTallaDTO.id())
                .orElseThrow(() -> new RuntimeException("ProductoTalla no encontrado"));
        productoTalla.setTalla(productoTallaDTO.talla());
        productoTalla.setCantidad(productoTallaDTO.cantidad());
        ProductoTalla productoTallaActualizado = productoTallaRepo.save(productoTalla);
        return new InformacionProductoTallaDTO(
                productoTallaActualizado.getId(),
                productoTallaActualizado.getProducto().getId_producto(),
                productoTallaActualizado.getTalla(),
                productoTallaActualizado.getCantidad()
        );
    }

    @Override
    public List<RegistroProductoTallaDTO> obtenerTallasPorProductoId(int productoId) {
        Producto producto = productoRepo.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return producto.getTallas().stream()
                .map(talla -> new RegistroProductoTallaDTO(
                        productoId,
                        talla.getTalla(),
                        talla.getCantidad()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProductoTalla(int id) {
        productoTallaRepo.deleteById(id);
    }
}