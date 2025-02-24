package com.example.miapi;

import com.example.miapi.controllers.ProductoController;
import com.example.miapi.models.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class ProductoControllerTest {

    @Autowired
    private ProductoController productoController;

    @Test
    public void testListaProductos() {
        // Llamamos al método reactivo que retorna un Flux de Producto
        Flux<Producto> productosFlux = productoController.listarProductos();

        // Verificamos con StepVerifier que el flujo emite los productos esperados en orden:
        StepVerifier.create(productosFlux)
            // El primer producto debe tener nombre "Laptop"
            .expectNextMatches(p -> p.getNombre().equals("Laptop"))
            // El segundo producto debe tener nombre "Mouse"
            .expectNextMatches(p -> p.getNombre().equals("Mouse"))
            // El tercer producto debe tener nombre "Teclado"
            .expectNextMatches(p -> p.getNombre().equals("Teclado"))
            // El flujo debe completarse sin errores
            .verifyComplete();
    }
}
