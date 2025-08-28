package org.jrae.gestion_biblioteca;

import org.jrae.gestion_biblioteca.dominio.service.IGeneroService;
import org.jrae.gestion_biblioteca.dominio.service.ILibroService;
import org.jrae.gestion_biblioteca.dominio.service.IUbicacionService;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GestionBibliotecaApplication implements CommandLineRunner {
    @Autowired
    private IGeneroService generoService;
    @Autowired
    private ILibroService libroService;
    @Autowired
    private IUbicacionService ubicacionService;
    private static final Logger logger = LoggerFactory.getLogger(GestionBibliotecaApplication.class);
    String sl = System.lineSeparator(); // Salto de linea

    public static void main(String[] args) {
        SpringApplication.run(GestionBibliotecaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    private void GestionBibliotecaClienteApp() {
        logger.info("************** Bienvenid@ a mi biblioteca **************");
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir) {
            var opcion = mostrarMenuConsola(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(sl);
        }
    }

    private int mostrarMenuConsola(Scanner consola) {
        logger.info("""
                \n************** Seleccione una opcion a continuacion: **************
                1. CRUD de Genero
                2. CRUD de Libros
                3. CRUD de Ubicacion
                4. Salir
                Elija una opcion: \s""");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info("""
                        \nElija una opcion en el CRUD:
                        1. Agregar nuevo Genero.
                        2. Eliminar un genero.
                        3. Editar un genero.
                        4. Listar los generos.
                        5. Buscar los libros por genero.
                        6. Listar genero por Id.
                        7. Salir.
                        Elija: \s""");
                var opcionClase = Integer.parseInt(consola.nextLine());
                switch (opcionClase) {
                    case 1 -> {
                        var genero = new Genero();
                        logger.info(sl +"************** Agregar un nuevo genero **************" + sl);
                        logger.info("Nombre del genero: " +sl);
                        genero.setTipo(consola.nextLine());
                        logger.info("Una descripcion para el genero(No más de 255 caracteres): " + sl);
                        genero.setDescripcion(consola.nextLine());
                        generoService.guardarGenero(genero);
                    }
                    case 2 -> {
                        logger.info(sl +"************** Eliminar un genero **************" + sl);
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        listadoGeneros.forEach( g -> logger.info(g.toString() + sl));
                        logger.info("Ingrese el codigo del genero a eliminar: ");
                        var genero = generoService.listarPorId(Integer.parseInt(consola.nextLine()));
                        if (genero != null){
                            generoService.eliminarGenero(genero);
                            logger.info("Genero eliminado: " + sl + genero + sl);
                        }else {
                            logger.info("No se encontro el genero: " + sl + genero + sl);
                        }
                    }
                    case 3 -> {
                        logger.info(sl+"************** Editar un genero **************" + sl);
                        logger.info("Ingrese el codigo del genero a editar: ");
                        var genero = generoService.listarPorId(Integer.parseInt(consola.nextLine()));
                        if (genero != null){
                            logger.info("Nombre del genero: " +sl);
                            genero.setTipo(consola.nextLine());
                            logger.info("Una descripcion para el genero(No más de 255 caracteres): " + sl);
                            genero.setDescripcion(consola.nextLine());
                            generoService.guardarGenero(genero);
                            logger.info("Genero modificado: " + sl + genero + sl);
                        }else{
                            logger.info("Genero no encontrado : " + sl + genero + sl);
                        }
                    }
                    case 4 -> {
                        logger.info(sl + "************** Listado de Todos los Generos **************" + sl);
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        listadoGeneros.forEach( g -> logger.info(g.toString() + sl));
                    }
                    case 5 -> {
                        logger.info(sl + "************** Buscar libros de un genero **************" + sl);
                        logger.info("Generos existentes: " + sl);
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        listadoGeneros.forEach( g -> logger.info(g.getTipo() + sl));
                        logger.info("Ingrese el nombre del genero a consultar: " + sl);
                        List<Libro> libros = generoService.listarPorGenero(consola.nextLine());
                        if (libros != null){
                            libros.forEach(l -> logger.info(l.toString() + sl));
                        }
                    }
                    case 6 -> {
                        logger.info(sl + "************** Genero de un ID **************" + sl);
                        logger.info("Ingrese el Id: " + sl);
                        var codigo = Integer.parseInt(consola.nextLine());
                        var genero = generoService.listarPorId(codigo);
                        if (genero != null){
                            logger.info("Genero encontrado: " + genero + sl);
                        }else {
                            logger.info("Genero con codigo " + codigo + " no encontrado: " + genero + sl);
                        }
                    }
                    case 7 -> {
                        logger.info("Saliendo...");
                        salir = true;
                    }
                    default -> {
                        logger.info("Numero incorrecto");
                    }
                }
            }
            case 2 -> {
                logger.info("""
                        \nElija una opcion en el CRUD:
                        1. Agregar nuevo Libro.
                        2. Eliminar un libro.
                        3. Editar un libro.
                        4. Listar los libros.
                        5. Buscar los libros por filtro.
                        6. Listar libro por Id.
                        7. Salir.
                        Elija: \s""");
                var opcionClase = Integer.parseInt(consola.nextLine());
                switch (opcionClase) {
                    case 1 -> {
                        var libro = new Libro();
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        logger.info(sl +"************** Agregar un nuevo libro **************" + sl);
                        logger.info("Nombre del libro: " +sl);
                        libro.setTitulo(consola.nextLine());
                        logger.info("Seleccione un genero: " +sl);
                        listadoGeneros.forEach( g -> logger.info("Genero: " + g.getTipo() + sl + "codigo del genero: " + g.getCodigoGenero() + sl));
                        logger.info("Escriba el genero o su codigo: " + sl);
                        var genero = consola.nextLine();
                        var bgenero = false;
                        if (bgenero = false){
                            if (genero.isEmpty() || genero == null){
                                return false;
                            }
                            try{
                                libro.setCodigoGenero(Integer.parseInt(genero));
                            }catch (NumberFormatException e){
                                if (generoService.listarPorGenero(genero) == null || generoService.listarPorGenero(genero).isEmpty()){
                                    logger.info("Valor invalido");
                                } else {
                                    generoService.listarPorGenero(genero)
                                }
                            }
                        }
                        libro.setDescripcion(consola.nextLine());
                        generoService.guardarGenero(libro);
                    }
                    case 2 -> {
                        logger.info(sl +"************** Eliminar un genero **************" + sl);
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        listadoGeneros.forEach( g -> logger.info(g.toString() + sl));
                        logger.info("Ingrese el codigo del genero a eliminar: ");
                        var genero = generoService.listarPorId(Integer.parseInt(consola.nextLine()));
                        if (genero != null){
                            generoService.eliminarGenero(genero);
                            logger.info("Genero eliminado: " + sl + genero + sl);
                        }else {
                            logger.info("No se encontro el genero: " + sl + genero + sl);
                        }
                    }
                    case 3 -> {
                        logger.info(sl+"************** Editar un genero **************" + sl);
                        logger.info("Ingrese el codigo del genero a editar: ");
                        var genero = generoService.listarPorId(Integer.parseInt(consola.nextLine()));
                        if (genero != null){
                            logger.info("Nombre del genero: " +sl);
                            genero.setTipo(consola.nextLine());
                            logger.info("Una descripcion para el genero(No más de 255 caracteres): " + sl);
                            genero.setDescripcion(consola.nextLine());
                            generoService.guardarGenero(genero);
                            logger.info("Genero modificado: " + sl + genero + sl);
                        }else{
                            logger.info("Genero no encontrado : " + sl + genero + sl);
                        }
                    }
                    case 4 -> {
                        logger.info(sl + "************** Listado de Todos los Generos **************" + sl);
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        listadoGeneros.forEach( g -> logger.info(g.toString() + sl));
                    }
                    case 5 -> {
                        logger.info(sl + "************** Buscar libros de un genero **************" + sl);
                        logger.info("Generos existentes: " + sl);
                        List<Genero> listadoGeneros = generoService.listarGeneros();
                        listadoGeneros.forEach( g -> logger.info(g.getTipo() + sl));
                        logger.info("Ingrese el nombre del genero a consultar: " + sl);
                        List<Libro> libros = generoService.listarPorGenero(consola.nextLine());
                        if (libros != null){
                            libros.forEach(l -> logger.info(l.toString() + sl));
                        }
                    }
                    case 6 -> {
                        logger.info(sl + "************** Genero de un ID **************" + sl);
                        logger.info("Ingrese el Id: " + sl);
                        var codigo = Integer.parseInt(consola.nextLine());
                        var genero = generoService.listarPorId(codigo);
                        if (genero != null){
                            logger.info("Genero encontrado: " + genero + sl);
                        }else {
                            logger.info("Genero con codigo " + codigo + " no encontrado: " + genero + sl);
                        }
                    }
                    case 7 -> {
                        logger.info("Saliendo...");
                        salir = true;
                    }
                    default -> {
                        logger.info("Numero incorrecto");
                    }
                }
            }
        }
        return salir;
    }
}
