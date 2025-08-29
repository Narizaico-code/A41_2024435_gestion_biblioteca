package org.jrae.gestion_biblioteca.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.jrae.gestion_biblioteca.dominio.service.IUbicacionService;
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
public class UbicacionesController {
    @Autowired
    private IUbicacionService ubicacionService;

    private List<Ubicacion> ubicaciones;
    private Ubicacion ubicacionSeleccionada;

    private static final Logger logger = LoggerFactory.getLogger(UbicacionesController.class);
    String sl = System.lineSeparator();

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.ubicaciones = this.ubicacionService.listarUbicaciones();
        this.ubicaciones.forEach(ubicacion -> logger.info(ubicacion.toString() + sl));
    }

    public void agregarUbicacion(){
        this.ubicacionSeleccionada = new Ubicacion();
    }

    public void guardarUbicacion(){
        logger.info("Ubicación a guardar: " + this.ubicacionSeleccionada + sl);

        if (this.ubicacionSeleccionada.getCodigoUbicacion() == null){
            this.ubicacionService.guardarUbicacion(this.ubicacionSeleccionada);
            this.ubicaciones.add(this.ubicacionSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ubicación agregada"));
        } else {
            this.ubicacionService.guardarUbicacion(this.ubicacionSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ubicación Actualizada"));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalUbicacion').hide()");
        PrimeFaces.current().ajax().update("formulario-ubicaciones:mensaje_emergente", "formulario-ubicaciones:tabla-ubicaciones");
        this.ubicacionSeleccionada = null;
    }

    public void eliminarUbicacion(){
        logger.info("Ubicación a eliminar: " + this.ubicacionSeleccionada + sl);
        this.ubicacionService.eliminarUbicacion(ubicacionSeleccionada);
        this.ubicaciones.remove(ubicacionSeleccionada);
        this.ubicacionSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ubicación eliminada"));
        PrimeFaces.current().ajax().update("formulario-ubicaciones:mensaje_emergente", "formulario-ubicaciones:tabla-ubicaciones");
    }
}