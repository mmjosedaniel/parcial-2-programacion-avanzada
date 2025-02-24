package com.example.miapi.controllers;

import com.example.miapi.models.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> productos = new ArrayList<>();

    // se crea la lista con los productos
    public ProductoController() {
        productos.add(new Producto("1", "Laptop", 1200.0));
        productos.add(new Producto("2", "Mouse", 25.0));
        productos.add(new Producto("3", "Teclado", 45.0));
    }

    // Agregar un producto (POST)
    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        productos.add(producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    // Listar todos los productos (GET) - Retorna de forma reactiva los productos
    @GetMapping
    public Flux<Producto> listarProductos() {
        return Flux.fromIterable(productos);
    }

    // Obtener un producto por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String id) {
        Optional<Producto> productoOpt = productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return productoOpt.map(producto ->
                new ResponseEntity<>(producto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un producto por ID (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String id, @RequestBody Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getId().equals(id)) {
                p.setNombre(productoActualizado.getNombre());
                p.setPrecio(productoActualizado.getPrecio());
                return new ResponseEntity<>(p, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un producto por ID (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        boolean eliminado = productos.removeIf(p -> p.getId().equals(id));
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
