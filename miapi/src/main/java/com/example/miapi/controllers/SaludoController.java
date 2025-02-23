package com.example.miapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Indica que la clase es un controlador REST
@RestController
// Establece la ruta base para todos los endpoints
@RequestMapping("/api")
public class SaludoController {

	// Peticiones HTTP GET
    @GetMapping("/saludo")
    // Metodo obtenerSaludo
    public String obtenerSaludo() {
        return "¡Hola, API RESTful!";
    }
}
