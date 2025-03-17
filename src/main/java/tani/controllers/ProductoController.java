package tani.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tani.dto.producto.ProductoConTallasDTO;
import tani.dto.producto.RegistroProductoDTO;
import tani.dto.producto.InformacionProductoDTO;
import tani.dto.productotalla.RegistroProductoTallaDTO;
import tani.services.interfaces.ProductoServicio;
import tani.services.interfaces.ProductoTallaServicio;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoServicio productoServicio;
    private final ProductoTallaServicio productoTallaServicio;

    @PostMapping("/crear-producto")
    public ResponseEntity<Map<String, String>> crearProducto(@Valid @RequestBody ProductoConTallasDTO productoConTallasDTO) throws Exception {
        InformacionProductoDTO informacionProductoDTO = productoServicio.crearProducto(productoConTallasDTO.getRegistroProductoDTO());
        productoTallaServicio.crearProductoTallas(productoConTallasDTO.getTallas(), informacionProductoDTO);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Producto creado exitosamente");

        return ResponseEntity.ok(response);
    }
}