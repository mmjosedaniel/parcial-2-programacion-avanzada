package com.example.miapi.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ConfiguracionInternacionalizacion {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // Se buscarán archivos cuyo nombre base sea "messages"
        messageSource.setBasename("messages");
        // Se usa UTF-8 para evitar problemas de codificación
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
