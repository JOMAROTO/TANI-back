package tani.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tani.dto.producto.RegistroProductoDTO;
import tani.dto.producto.InformacionProductoDTO;
import tani.dto.producto.EditarProductoDTO;
import tani.model.entities.Producto;
import tani.repositories.ProductoRepo;
import tani.services.interfaces.ProductoServicio;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    @Override
    public InformacionProductoDTO crearProducto(RegistroProductoDTO productoDTO) {

        if (productoRepo.findByNombre(productoDTO.nombre()).isPresent()) {
            throw new RuntimeException("El nombre del producto ya está registrado.");
        }

        //Convertir DTO a entidad Producto
        Producto producto = new Producto();
        producto.setNombre(productoDTO.nombre());
        producto.setDescripcion(productoDTO.descripcion());
        producto.setTipoCalzado(productoDTO.tipoCalzado());
        producto.setImagen(productoDTO.imagen());
        producto.setPrecio(productoDTO.precio());

        //Guardar producto en la base de datos
        Producto productoCreado = productoRepo.save(producto);



        //Convertir entidad Producto a DTO y retornar
        return new InformacionProductoDTO(
                productoCreado.getId_producto(),
                productoCreado.getNombre(),
                productoCreado.getDescripcion(),
                productoCreado.getTipoCalzado(),
                productoCreado.getImagen(),
                productoCreado.getPrecio()
        );
    }

    @Override
    public InformacionProductoDTO obtenerProductoPorId(int id) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return new InformacionProductoDTO(
                producto.getId_producto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getTipoCalzado(),
                producto.getImagen(),
                producto.getPrecio()
        );
    }

    @Override
    public List<InformacionProductoDTO> listarProductos() {
        return productoRepo.findAll().stream()
                .map(producto -> new InformacionProductoDTO(
                        producto.getId_producto(),
                        producto.getNombre(),
                        producto.getDescripcion(),
                        producto.getTipoCalzado(),
                        producto.getImagen(),
                        producto.getPrecio()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public InformacionProductoDTO editarProducto(EditarProductoDTO productoDTO) {
        Producto producto = productoRepo.findById(productoDTO.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(productoDTO.nombre());
        producto.setDescripcion(productoDTO.descripcion());
        producto.setTipoCalzado(productoDTO.tipoCalzado());
        producto.setImagen(productoDTO.imagen());
        producto.setPrecio(productoDTO.precio());
        Producto productoActualizado = productoRepo.save(producto);
        return new InformacionProductoDTO(
                productoActualizado.getId_producto(),
                productoActualizado.getNombre(),
                productoActualizado.getDescripcion(),
                productoActualizado.getTipoCalzado(),
                productoActualizado.getImagen(),
                productoActualizado.getPrecio()
        );
    }

    @Override
    public void eliminarProducto(int id) {
        productoRepo.deleteById(id);
    }
}