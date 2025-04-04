package tani.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tani.model.entities.Producto;
import tani.model.entities.ProductoTalla;
import tani.model.enums.TIPO_CALZADO;
import tani.repositories.ProductoRepo;
import tani.repositories.ProductoTallaRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private ProductoTallaRepo productoTallaRepo;

    @Test
    public void registrarTest(){
        Producto producto = Producto.builder()
                .nombre("Calzado Deportivo")
                .descripcion("Ideales para hacer deporte.")
                .tipoCalzado(TIPO_CALZADO.TENIS)
                .imagen("running.png")
                .precio(200.99f)
                .tallas(new ArrayList<>())
                .build();

        List<ProductoTalla> tallas = new ArrayList<>();
        tallas.add(
                ProductoTalla.builder()
                        .talla("38")
                        .cantidad(10)
                        .producto(producto)
                        .build()
        );
        tallas.add(
                ProductoTalla.builder()
                        .talla("40")
                        .cantidad(5)
                        .producto(producto)
                        .build()
        );

        producto.setTallas(tallas);

        Producto productoCreado= productoRepo.save(producto);
        assertNotNull(productoCreado);
        assertEquals(2, productoCreado.getTallas().size());
        assertEquals("38", productoCreado.getTallas().get(0).getTalla());
    }

    @Test
    public void actualizarTest(){
        Producto producto = productoRepo.findById(16).orElseThrow();
        producto.setNombre("Calzado elegante");
        producto.setTipoCalzado(TIPO_CALZADO.ZAPATO);
        producto.setDescripcion("Ideales para salidas casuales");
        producto.setImagen("elegante.png");
        producto.setPrecio(90.0f);

        productoRepo.save(producto);
        Producto productoActualizado = productoRepo.findById(16).orElseThrow();
        assertEquals("Calzado elegante", productoActualizado.getNombre());
    }

    @Test
    public void listarTest(){
        List<Producto> lista = productoRepo.findAllWithTallas();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "La lista no debe estar vacía");

        lista.forEach(producto -> {
            System.out.println("\n=== Producto ===");
            System.out.println("ID: " + producto.getId_producto());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Tipo: " + producto.getTipoCalzado());
            assertTrue(producto.getId_producto() > 0, "ID invalido");
            assertNotNull(producto.getNombre(), "Nombre no debe ser nulo");
            assertNotNull(producto.getTipoCalzado(), "Tipo de calzado no debe ser nulo");

            if(!producto.getTallas().isEmpty()){
                System.out.println("Tallas:");
                producto.getTallas().forEach(productoTalla -> {
                    System.out.println(" - Talla: " + productoTalla.getTalla() + ", Cantidad: " + productoTalla.getCantidad());
                    assertNotNull(productoTalla.getTalla(), "Talla no debe ser nula");
                    assertTrue(productoTalla.getCantidad() >= 0, "Cantidad no puede ser negativa");
                });
            }
        });
    }

    @Test
    public void eliminarTest(){
        int idExistente = 14;
        Producto producto = productoRepo.findById(idExistente)
                .orElseThrow(() -> new AssertionError("El producto no existe en la base de datos"));
        productoRepo.deleteById(idExistente);

        assertFalse(productoRepo.existsById(idExistente), "El producto no fue eliminado");

        List<ProductoTalla> tallas = productoTallaRepo.buscarPorIdProducto(idExistente);
        assertTrue(tallas.isEmpty(), "Las tallas asociadas no se eliminaron");
    }
}
