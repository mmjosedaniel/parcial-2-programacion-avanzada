package com.example.miapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/saludo")
    public String obtenerSaludo(@RequestParam(value = "lang", required = false) String lang) {
        // Si se especifica el parámetro "lang", se crea el Locale correspondiente.
        // En caso contrario, se usa un Locale predeterminado.
        Locale locale;
        if (lang != null && !lang.isEmpty()) {
            // Se utiliza Locale.forLanguageTag() para crear el Locale a partir del string
            locale = Locale.forLanguageTag(lang);
        } else {
            // Valor por defecto
            locale = new Locale("es");
        }
        // Se obtiene el mensaje asociado
        return messageSource.getMessage("saludo", null, locale);
    }
}
