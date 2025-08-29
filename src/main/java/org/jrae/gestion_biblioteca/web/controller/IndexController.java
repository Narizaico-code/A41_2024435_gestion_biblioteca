package org.jrae.gestion_biblioteca.web.controller;

import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Data
@ViewScoped
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    String sl = System.lineSeparator();

    public String irAutores() {
        return "autores?faces-redirect=true";
    }

    public String irALibros() {
        return "libros?faces-redirect=true";
    }

    public String irAUbicaciones() {
        return "ubicaciones?faces-redirect=true";
    }

    public String irAGeneros() {
        return "generos?faces-redirect=true";
    }
}