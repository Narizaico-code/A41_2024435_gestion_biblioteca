package org.jrae.gestion_biblioteca.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.jrae.gestion_biblioteca.dominio.service.IAutorService;
import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@ViewScoped
public class AutoresController {
    @Autowired
    private IAutorService autorService;

    private List<Autor> autores;
    private Autor autorSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(AutoresController.class);
    String sl = System.lineSeparator();

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.autores = this.autorService.listarAutores();
        this.autores.forEach(autor -> logger.info(autor.toString() + sl));
    }

    public void agregarAutor(){
        this.autorSeleccionado = new Autor();
    }

    public void guardarAutor(){
        logger.info("Autor a guardar: " + this.autorSeleccionado + sl);

        if (this.autorSeleccionado.getCodigoAutor() == null){
            this.autorService.guardarAutor(this.autorSeleccionado);
            this.autores.add(this.autorSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Autor agregado"));
        } else {
            this.autorService.guardarAutor(this.autorSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Autor Actualizado"));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalAutor').hide()");
        PrimeFaces.current().ajax().update("formulario-autores:mensaje_emergente", "formulario-autores:tabla-autores");
        this.autorSeleccionado = null;
    }

    public void eliminarAutor(){
        logger.info("Autor a eliminar: " + this.autorSeleccionado + sl);
        this.autorService.eliminarAutor(autorSeleccionado);
        this.autores.remove(autorSeleccionado);
        this.autorSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Autor eliminado"));
        PrimeFaces.current().ajax().update("formulario-autores:mensaje_emergente", "formulario-autores:tabla-autores");
    }
}