package org.jrae.gestion_biblioteca.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.jrae.gestion_biblioteca.dominio.service.ILibroService;
import org.jrae.gestion_biblioteca.dominio.service.IAutorService;
import org.jrae.gestion_biblioteca.dominio.service.IGeneroService;
import org.jrae.gestion_biblioteca.dominio.service.IUbicacionService;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Ubicacion;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@ViewScoped
public class LibrosController {
    @Autowired
    private ILibroService libroService;

    @Autowired
    private IAutorService autorService;

    @Autowired
    private IGeneroService generoService;

    @Autowired
    private IUbicacionService ubicacionService;

    private List<Libro> libros;
    private List<Autor> autores;
    private List<Genero> generos;
    private List<Ubicacion> ubicaciones;
    private Libro libroSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(LibrosController.class);
    String sl = System.lineSeparator();

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.libros = this.libroService.listarLibros();
        this.autores = this.autorService.listarAutores();
        this.generos = this.generoService.listarGeneros();
        this.ubicaciones = this.ubicacionService.listarUbicaciones();
        this.libros.forEach(libro -> logger.info(libro.toString() + sl));
    }

    public void agregarLibro(){
        this.libroSeleccionado = new Libro();
    }

    public void guardarLibro(){
        logger.info("Libro a guardar: " + this.libroSeleccionado + sl);

        if (this.libroSeleccionado.getCodigoLibro() == null){
            this.libroService.guardarLibro(this.libroSeleccionado);
            this.libros.add(this.libroSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro agregado"));
        } else {
            this.libroService.guardarLibro(this.libroSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro Actualizado"));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalLibro').hide()");
        PrimeFaces.current().ajax().update("formulario-libros:mensaje_emergente", "formulario-libros:tabla-libros");
        this.libroSeleccionado = null;
    }

    public void eliminarLibro(){
        logger.info("Libro a eliminar: " + this.libroSeleccionado + sl);
        this.libroService.eliminarLibro(libroSeleccionado);
        this.libros.remove(libroSeleccionado);
        this.libroSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro eliminado"));
        PrimeFaces.current().ajax().update("formulario-libros:mensaje_emergente", "formulario-libros:tabla-libros");
    }

    public void editarLibro(Libro libro) {
        this.libroSeleccionado = libro;
    }
}