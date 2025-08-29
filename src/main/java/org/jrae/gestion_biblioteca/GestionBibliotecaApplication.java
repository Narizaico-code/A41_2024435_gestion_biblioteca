package org.jrae.gestion_biblioteca;

import org.jrae.gestion_biblioteca.dominio.service.IAutorService;
import org.jrae.gestion_biblioteca.dominio.service.IGeneroService;
import org.jrae.gestion_biblioteca.dominio.service.ILibroService;
import org.jrae.gestion_biblioteca.dominio.service.IUbicacionService;
import org.jrae.gestion_biblioteca.persistence.entity.Autor;
import org.jrae.gestion_biblioteca.persistence.entity.Genero;
import org.jrae.gestion_biblioteca.persistence.entity.Libro;
import org.jrae.gestion_biblioteca.persistence.entity.Ubicacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
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
    @Autowired
    private IAutorService autorService;
    private static final Logger logger = LoggerFactory.getLogger(GestionBibliotecaApplication.class);
    String sl = System.lineSeparator();

    public static void main(String[] args) {
        SpringApplication.run(GestionBibliotecaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        GestionBibliotecaClienteApp();
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
                4. CRUD de Autor
                5. Salir
                Elija una opcion: \s""");
        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> salir = menuGeneros(consola);
            case 2 -> salir = menuLibros(consola);
            case 3 -> salir = menuUbicaciones(consola);
            case 4 -> salir = menuAutores(consola);
            case 5 -> {
                logger.info("Saliendo de la aplicación...");
                salir = true;
            }
            default -> logger.info("Opción no válida. Intente nuevamente.");
        }
        return salir;
    }

    private boolean menuGeneros(Scanner consola) {
        logger.info("""
                \nElija una opcion en el CRUD:
                1. Agregar nuevo Genero.
                2. Eliminar un genero.
                3. Editar un genero.
                4. Listar los generos.
                5. Buscar los libros por genero.
                6. Buscar genero por Id.
                7. Salir.
                Elija: \s""");
        var opcionClase = Integer.parseInt(consola.nextLine());

        switch (opcionClase) {
            case 1 -> {
                var genero = new Genero();
                logger.info(sl + "************** Agregar un nuevo genero **************" + sl);
                logger.info("Nombre del genero: ");
                genero.setTipo(consola.nextLine());
                logger.info("Una descripcion para el genero(No más de 255 caracteres): ");
                genero.setDescripcion(consola.nextLine());
                generoService.guardarGenero(genero);
            }
            case 2 -> {
                logger.info(sl + "************** Eliminar un genero **************" + sl);
                List<Genero> listadoGeneros = generoService.listarGeneros();
                listadoGeneros.forEach(g -> logger.info(g.toString() + sl));
                logger.info("Ingrese el codigo del genero a eliminar: ");
                var genero = generoService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (genero != null) {
                    generoService.eliminarGenero(genero);
                    logger.info("Genero eliminado: " + sl + genero + sl);
                } else {
                    logger.info("No se encontro el genero.");
                }
            }
            case 3 -> {
                logger.info(sl + "************** Editar un genero **************" + sl);
                logger.info("Ingrese el codigo del genero a editar: ");
                var genero = generoService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (genero != null) {
                    logger.info("Nombre del genero: ");
                    genero.setTipo(consola.nextLine());
                    logger.info("Una descripcion para the genero(No más de 255 caracteres): ");
                    genero.setDescripcion(consola.nextLine());
                    generoService.guardarGenero(genero);
                    logger.info("Genero modificado: " + sl + genero + sl);
                } else {
                    logger.info("Genero no encontrado.");
                }
            }
            case 4 -> {
                logger.info(sl + "************** Listado de Todos los Generos **************" + sl);
                List<Genero> listadoGeneros = generoService.listarGeneros();
                listadoGeneros.forEach(g -> logger.info(g.toString() + sl));
            }
            case 5 -> {
                logger.info(sl + "************** Buscar libros de un genero **************" + sl);
                logger.info("Generos existentes: " + sl);
                List<Genero> listadoGeneros = generoService.listarGeneros();
                listadoGeneros.forEach(g -> logger.info(g.getTipo() + sl));
                logger.info("Ingrese el nombre del genero a consultar: " + sl);
                List<Libro> libros = generoService.listarLibrosPorGenero(consola.nextLine());
                if (libros != null && !libros.isEmpty()) {
                    libros.forEach(l -> logger.info(l.toString() + sl));
                } else {
                    logger.info("No se encontraron libros para este genero." + sl);
                }
            }
            case 6 -> {
                logger.info(sl + "************** Genero de un ID **************" + sl);
                logger.info("Ingrese el Id: " + sl);
                var codigo = Integer.parseInt(consola.nextLine());
                var genero = generoService.buscarPorId(codigo);
                if (genero != null) {
                    logger.info("Genero encontrado: " + genero + sl);
                } else {
                    logger.info("Genero con codigo " + codigo + " no encontrado." + sl);
                }
            }
            case 7 -> {
                return true;
            }
            default -> logger.info("Numero incorrecto");
        }
        return false;
    }

    private boolean menuLibros(Scanner consola) {
        logger.info("""
                \nElija una opcion en el CRUD:
                1. Agregar nuevo Libro.
                2. Eliminar un libro.
                3. Editar an libro.
                4. Listar los libros.
                5. Buscar los libros por filtro.
                6. Buscar libro por Id.
                7. Salir.
                Elija: \s""");
        var opcionClase = Integer.parseInt(consola.nextLine());

        switch (opcionClase) {
            case 1 -> {
                var libro = new Libro();
                List<Genero> listadoGeneros = generoService.listarGeneros();
                List<Autor> listadoAutores = autorService.listarAutores();
                List<Ubicacion> listadoUbicaciones = ubicacionService.listarUbicaciones();

                logger.info(sl + "************** Agregar un nuevo libro **************" + sl);
                logger.info("Título del libro: ");
                libro.setTitulo(consola.nextLine());

                // Solicitar género
                logger.info("Seleccione un género: " + sl);
                listadoGeneros.forEach(g -> logger.info("Género: " + g.getTipo() + sl + "código del género: " + g.getCodigoGenero() + sl));
                logger.info("Escriba el género o su código: " + sl);
                var inputGenero = consola.nextLine();
                Genero genero = null;

                if (inputGenero.isEmpty()) {
                    logger.info("Debe ingresar un género válido.");
                    break;
                }

                try {
                    Integer codigoGenero = Integer.parseInt(inputGenero);
                    genero = generoService.buscarPorId(codigoGenero);
                } catch (NumberFormatException e) {
                    genero = generoService.buscarPorTipo(inputGenero);
                }

                if (genero == null) {
                    logger.info("Género no válido.");
                    break;
                }
                libro.setCodigoGenero(genero.getCodigoGenero());

                logger.info("Seleccione un autor: " + sl);
                listadoAutores.forEach(a -> logger.info("Autor: " + a.getNombre() + sl + "código del autor: " + a.getCodigoAutor() + sl));
                logger.info("Escriba el autor o su código: " + sl);
                var inputAutor = consola.nextLine();
                Autor autor = null;

                if (inputAutor.isEmpty()) {
                    logger.info("Debe ingresar un autor válido.");
                    break;
                }

                try {
                    Integer codigoAutor = Integer.parseInt(inputAutor);
                    autor = autorService.buscarPorId(codigoAutor);
                } catch (NumberFormatException e) {
                    autor = autorService.buscarPorNombre(inputAutor);
                }

                if (autor == null) {
                    logger.info("Autor no válido.");
                    break;
                }
                libro.setCodigoAutor(autor.getCodigoAutor());

                logger.info("Seleccione una ubicación: " + sl);
                listadoUbicaciones.forEach(u -> logger.info("Ubicación: " + u.getEdificio() + " - " + u.getSalon() + " - " + u.getEstanteria() + " - Fila: " + u.getFila() + sl + "código de la ubicación: " + u.getCodigoUbicacion() + sl));
                logger.info("Escriba el código de la ubicación: " + sl);
                var inputUbicacion = consola.nextLine();
                Ubicacion ubicacion = null;

                if (inputUbicacion.isEmpty()) {
                    logger.info("Debe ingresar una ubicación válida.");
                    break;
                }

                try {
                    Integer codigoUbicacion = Integer.parseInt(inputUbicacion);
                    ubicacion = ubicacionService.buscarPorId(codigoUbicacion);
                } catch (NumberFormatException e) {
                    logger.info("Debe ingresar un código numérico para la ubicación.");
                    break;
                }

                if (ubicacion == null) {
                    logger.info("Ubicación no válida.");
                    break;
                }
                libro.setCodigoUbicacion(ubicacion.getCodigoUbicacion());

                logger.info("Fecha de publicación (yyyy-mm-dd): ");
                String fechaStr = consola.nextLine();
                if (!fechaStr.isEmpty()) {
                    libro.setFechaPublicacion(LocalDate.parse(fechaStr));
                }

                logger.info("Cantidad: ");
                String cantidadStr = consola.nextLine();
                if (!cantidadStr.isEmpty()) {
                    libro.setCantidad(Integer.parseInt(cantidadStr));
                }

                logger.info("Disponibilidad (Disponible/No disponible): ");
                libro.setDisponibilidad(consola.nextLine());

                libroService.guardarLibro(libro);
                logger.info("Libro agregado correctamente.");
            }
            case 2 -> {
                logger.info(sl + "************** Eliminar un libro **************" + sl);
                List<Libro> listadoLibros = libroService.listarLibros();
                listadoLibros.forEach(l -> logger.info(l.toString() + sl));
                logger.info("Ingrese el codigo del libro a eliminar: ");
                var libro = libroService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (libro != null) {
                    libroService.eliminarLibro(libro);
                    logger.info("Libro eliminado: " + sl + libro + sl);
                } else {
                    logger.info("No se encontro el libro.");
                }
            }
            case 3 -> {
                logger.info(sl + "************** Editar un libro **************" + sl);
                logger.info("Ingrese el codigo del libro a editar: ");
                var libro = libroService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (libro != null) {
                    logger.info("Título del libro: ");
                    libro.setTitulo(consola.nextLine());

                    // Editar género
                    logger.info("Géneros disponibles: " + sl);
                    List<Genero> listadoGeneros = generoService.listarGeneros();
                    listadoGeneros.forEach(g -> logger.info("Género: " + g.getTipo() + " - Código: " + g.getCodigoGenero() + sl));
                    logger.info("Escriba el género o su código: " + sl);
                    var inputGenero = consola.nextLine();
                    Genero genero = null;

                    try {
                        Integer codigoGenero = Integer.parseInt(inputGenero);
                        genero = generoService.buscarPorId(codigoGenero);
                    } catch (NumberFormatException e) {
                        genero = generoService.buscarPorTipo(inputGenero);
                    }

                    if (genero != null) {
                        libro.setCodigoGenero(genero.getCodigoGenero());
                    }

                    // Editar autor
                    logger.info("Autores disponibles: " + sl);
                    List<Autor> listadoAutores = autorService.listarAutores();
                    listadoAutores.forEach(a -> logger.info("Autor: " + a.getNombre() + " - Código: " + a.getCodigoAutor() + sl));
                    logger.info("Escriba el autor o su código: " + sl);
                    var inputAutor = consola.nextLine();
                    Autor autor = null;

                    try {
                        Integer codigoAutor = Integer.parseInt(inputAutor);
                        autor = autorService.buscarPorId(codigoAutor);
                    } catch (NumberFormatException e) {
                        autor = autorService.buscarPorNombre(inputAutor);
                    }

                    if (autor != null) {
                        libro.setCodigoAutor(autor.getCodigoAutor());
                    }

                    // Editar ubicación
                    logger.info("Ubicaciones disponibles: " + sl);
                    List<Ubicacion> listadoUbicaciones = ubicacionService.listarUbicaciones();
                    listadoUbicaciones.forEach(u -> logger.info("Ubicación: " + u.getEdificio() + " - " + u.getSalon() + " - " + u.getEstanteria() + " - Fila: " + u.getFila() + " - Código: " + u.getCodigoUbicacion() + sl));
                    logger.info("Escriba el código de la ubicación: " + sl);
                    var inputUbicacion = consola.nextLine();
                    Ubicacion ubicacion = null;

                    try {
                        Integer codigoUbicacion = Integer.parseInt(inputUbicacion);
                        ubicacion = ubicacionService.buscarPorId(codigoUbicacion);
                    } catch (NumberFormatException e) {
                        logger.info("Debe ingresar un código numérico para la ubicación.");
                    }

                    if (ubicacion != null) {
                        libro.setCodigoUbicacion(ubicacion.getCodigoUbicacion());
                    }

                    logger.info("Nueva fecha de publicación (yyyy-mm-dd): ");
                    String fechaStr = consola.nextLine();
                    if (!fechaStr.isEmpty()) {
                        libro.setFechaPublicacion(LocalDate.parse(fechaStr));
                    }

                    logger.info("Nueva cantidad: ");
                    String cantidadStr = consola.nextLine();
                    if (!cantidadStr.isEmpty()) {
                        libro.setCantidad(Integer.parseInt(cantidadStr));
                    }

                    logger.info("Nueva disponibilidad (Disponible/No disponible): ");
                    libro.setDisponibilidad(consola.nextLine());

                    libroService.guardarLibro(libro);
                    logger.info("Libro modificado: " + sl + libro + sl);
                } else {
                    logger.info("Libro no encontrado.");
                }
            }
            case 4 -> {
                logger.info(sl + "************** Listado de Todos los Libros **************" + sl);
                List<Libro> listadoLibros = libroService.listarLibros();
                listadoLibros.forEach(l -> logger.info(l.toString() + sl));
            }
            case 5 -> {
                logger.info(sl + "************** Buscar libros por filtro **************" + sl);
                logger.info("1. Por título\n2. Por autor\n3. Por género\n4. Por ubicación");
                logger.info("Elija opción: ");
                var filtro = Integer.parseInt(consola.nextLine());
                switch (filtro) {
                    case 1 -> {
                        logger.info("Ingrese título: ");
                        String titulo = consola.nextLine();
                        List<Libro> libros = libroService.buscarPorTitulo(titulo);
                        if (libros != null && !libros.isEmpty()) {
                            libros.forEach(l -> logger.info(l.toString() + sl));
                        } else {
                            logger.info("No se encontraron libros con ese título." + sl);
                        }
                    }
                    case 2 -> {
                        logger.info("Autores disponibles: " + sl);
                        List<Autor> autores = autorService.listarAutores();
                        autores.forEach(a -> logger.info(a.getNombre() + " - Código: " + a.getCodigoAutor() + sl));
                        logger.info("Ingrese nombre o código de autor: " + sl);
                        String inputAutor = consola.nextLine();
                        List<Libro> libros;

                        try {
                            Integer codigoAutor = Integer.parseInt(inputAutor);
                            Autor autor = autorService.buscarPorId(codigoAutor);
                            libros = autor != null ? autorService.listarLibrosPorAutor(autor.getNombre()) : List.of();
                        } catch (NumberFormatException e) {
                            libros = autorService.listarLibrosPorAutor(inputAutor);
                        }

                        if (libros != null && !libros.isEmpty()) {
                            libros.forEach(l -> logger.info(l.toString() + sl));
                        } else {
                            logger.info("No se encontraron libros para este autor." + sl);
                        }
                    }
                    case 3 -> {
                        logger.info("Géneros disponibles: " + sl);
                        List<Genero> generos = generoService.listarGeneros();
                        generos.forEach(g -> logger.info(g.getTipo() + " - Código: " + g.getCodigoGenero() + sl));
                        logger.info("Ingrese nombre o código de género: " + sl);
                        String inputGenero = consola.nextLine();
                        List<Libro> libros;

                        try {
                            Integer codigoGenero = Integer.parseInt(inputGenero);
                            Genero genero = generoService.buscarPorId(codigoGenero);
                            libros = genero != null ? generoService.listarLibrosPorGenero(genero.getTipo()) : List.of();
                        } catch (NumberFormatException e) {
                            libros = generoService.listarLibrosPorGenero(inputGenero);
                        }

                        if (libros != null && !libros.isEmpty()) {
                            libros.forEach(l -> logger.info(l.toString() + sl));
                        } else {
                            logger.info("No se encontraron libros para este género." + sl);
                        }
                    }
                    case 4 -> {
                        logger.info("Ubicaciones disponibles: " + sl);
                        List<Ubicacion> ubicaciones = ubicacionService.listarUbicaciones();
                        ubicaciones.forEach(u -> logger.info("Código: " + u.getCodigoUbicacion() + " - Edificio: " + u.getEdificio() + ", Salon: " + u.getSalon() + ", Estanteria: " + u.getEstanteria() + ", Fila: " + u.getFila() + sl));
                        logger.info("Ingrese el código de la ubicación: " + sl);
                        Integer codigoUbicacion = Integer.parseInt(consola.nextLine());
                        List<Libro> libros = ubicacionService.listarLibrosPorUbicacion(codigoUbicacion);

                        if (libros != null && !libros.isEmpty()) {
                            libros.forEach(l -> logger.info(l.toString() + sl));
                        } else {
                            logger.info("No se encontraron libros para esta ubicación." + sl);
                        }
                    }
                    default -> logger.info("Opción no válida." + sl);
                }
            }
            case 6 -> {
                logger.info(sl + "************** Libro de un ID **************" + sl);
                logger.info("Ingrese el Id: " + sl);
                var codigo = Integer.parseInt(consola.nextLine());
                var libro = libroService.buscarPorId(codigo);
                if (libro != null) {
                    logger.info("Libro encontrado: " + libro + sl);
                } else {
                    logger.info("Libro con codigo " + codigo + " no encontrado." + sl);
                }
            }
            case 7 -> {
                return true;
            }
            default -> logger.info("Numero incorrecto");
        }
        return false;
    }

    private boolean menuUbicaciones(Scanner consola) {
        logger.info("""
                \nElija una opcion en el CRUD de Ubicacion:
                1. Agregar nueva Ubicacion.
                2. Eliminar una ubicacion.
                3. Editar una ubicacion.
                4. Listar las ubicaciones.
                5. Buscar ubicacion por Id.
                6. Listar libros por ubicacion.
                7. Salir.
                Elija: \s""");
        var opcionClase = Integer.parseInt(consola.nextLine());

        switch (opcionClase) {
            case 1 -> {
                var ubicacion = new Ubicacion();
                logger.info(sl + "************** Agregar una nueva ubicacion **************" + sl);
                logger.info("Edificio: ");
                ubicacion.setEdificio(consola.nextLine());
                logger.info("Salón: ");
                ubicacion.setSalon(consola.nextLine());
                logger.info("Estantería: ");
                ubicacion.setEstanteria(consola.nextLine());
                logger.info("Fila: ");
                ubicacion.setFila(Integer.parseInt(consola.nextLine()));
                ubicacionService.guardarUbicacion(ubicacion);
            }
            case 2 -> {
                logger.info(sl + "************** Eliminar una ubicacion **************" + sl);
                List<Ubicacion> listadoUbicaciones = ubicacionService.listarUbicaciones();
                listadoUbicaciones.forEach(u -> logger.info(u.toString() + sl));
                logger.info("Ingrese el codigo de la ubicacion a eliminar: ");
                var ubicacion = ubicacionService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (ubicacion != null) {
                    ubicacionService.eliminarUbicacion(ubicacion);
                    logger.info("Ubicacion eliminada: " + sl + ubicacion + sl);
                } else {
                    logger.info("No se encontro la ubicacion.");
                }
            }
            case 3 -> {
                logger.info(sl + "************** Editar una ubicacion **************" + sl);
                logger.info("Ingrese el codigo de la ubicacion a editar: ");
                var ubicacion = ubicacionService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (ubicacion != null) {
                    logger.info("Edificio: ");
                    ubicacion.setEdificio(consola.nextLine());
                    logger.info("Salón: ");
                    ubicacion.setSalon(consola.nextLine());
                    logger.info("Estantería: ");
                    ubicacion.setEstanteria(consola.nextLine());
                    logger.info("Fila: ");
                    ubicacion.setFila(Integer.parseInt(consola.nextLine()));
                    ubicacionService.guardarUbicacion(ubicacion);
                    logger.info("Ubicacion modificada: " + sl + ubicacion + sl);
                } else {
                    logger.info("Ubicacion no encontrada.");
                }
            }
            case 4 -> {
                logger.info(sl + "************** Listado de Todas las Ubicaciones **************" + sl);
                List<Ubicacion> listadoUbicaciones = ubicacionService.listarUbicaciones();
                listadoUbicaciones.forEach(u -> logger.info(u.toString() + sl));
            }
            case 5 -> {
                logger.info(sl + "************** Ubicacion de un ID **************" + sl);
                logger.info("Ingrese el Id: " + sl);
                var codigo = Integer.parseInt(consola.nextLine());
                var ubicacion = ubicacionService.buscarPorId(codigo);
                if (ubicacion != null) {
                    logger.info("Ubicacion encontrada: " + ubicacion + sl);
                } else {
                    logger.info("Ubicacion con codigo " + codigo + " no encontrada." + sl);
                }
            }
            case 6 -> {
                logger.info(sl + "************** Buscar libros de una ubicacion **************" + sl);
                logger.info("Ubicaciones existentes: " + sl);
                List<Ubicacion> listadoUbicaciones = ubicacionService.listarUbicaciones();
                listadoUbicaciones.forEach(u -> logger.info("Código: " + u.getCodigoUbicacion() + " - Edificio: " + u.getEdificio() + ", Salon: " + u.getSalon() + ", Estanteria: " + u.getEstanteria() + ", Fila: " + u.getFila() + sl));
                logger.info("Ingrese el codigo de la ubicacion a consultar: " + sl);
                Integer codigo = Integer.parseInt(consola.nextLine());
                List<Libro> libros = ubicacionService.listarLibrosPorUbicacion(codigo);
                if (libros != null && !libros.isEmpty()) {
                    libros.forEach(l -> logger.info(l.toString() + sl));
                } else {
                    logger.info("No se encontraron libros para esta ubicacion." + sl);
                }
            }
            case 7 -> {
                return true;
            }
            default -> logger.info("Numero incorrecto");
        }
        return false;
    }

    private boolean menuAutores(Scanner consola) {
        logger.info("""
                \nElija una opcion in the CRUD de Autor:
                1. Agregar nuevo Autor.
                2. Eliminar an autor.
                3. Editar an autor.
                4. Listar los autores.
                5. Buscar autor por Id.
                6. Listar libros por autor.
                7. Salir.
                Elija: \s""");
        var opcionClase = Integer.parseInt(consola.nextLine());

        switch (opcionClase) {
            case 1 -> {
                var autor = new Autor();
                logger.info(sl + "************** Agregar un nuevo autor **************" + sl);
                logger.info("Nombre del autor: ");
                autor.setNombre(consola.nextLine());
                logger.info("Nacionalidad del autor: ");
                autor.setNacionalidad(consola.nextLine());
                autorService.guardarAutor(autor);
            }
            case 2 -> {
                logger.info(sl + "************** Eliminar un autor **************" + sl);
                List<Autor> listadoAutores = autorService.listarAutores();
                listadoAutores.forEach(a -> logger.info(a.toString() + sl));
                logger.info("Ingrese el codigo del autor a eliminar: ");
                var autor = autorService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (autor != null) {
                    autorService.eliminarAutor(autor);
                    logger.info("Autor eliminado: " + sl + autor + sl);
                } else {
                    logger.info("No se encontro el autor.");
                }
            }
            case 3 -> {
                logger.info(sl + "************** Editar un autor **************" + sl);
                logger.info("Ingrese el codigo del autor a editar: ");
                var autor = autorService.buscarPorId(Integer.parseInt(consola.nextLine()));
                if (autor != null) {
                    logger.info("Nombre del autor: ");
                    autor.setNombre(consola.nextLine());
                    logger.info("Nacionalidad del autor: ");
                    autor.setNacionalidad(consola.nextLine());
                    autorService.guardarAutor(autor);
                    logger.info("Autor modificado: " + sl + autor + sl);
                } else {
                    logger.info("Autor no encontrado.");
                }
            }
            case 4 -> {
                logger.info(sl + "************** Listado de Todos los Autores **************" + sl);
                List<Autor> listadoAutores = autorService.listarAutores();
                listadoAutores.forEach(a -> logger.info(a.toString() + sl));
            }
            case 5 -> {
                logger.info(sl + "************** Autor de un ID **************" + sl);
                logger.info("Ingrese el Id: " + sl);
                var codigo = Integer.parseInt(consola.nextLine());
                var autor = autorService.buscarPorId(codigo);
                if (autor != null) {
                    logger.info("Autor encontrado: " + autor + sl);
                } else {
                    logger.info("Autor con codigo " + codigo + " no encontrado." + sl);
                }
            }
            case 6 -> {
                logger.info(sl + "************** Buscar libros de un autor **************" + sl);
                logger.info("Autores existentes: " + sl);
                List<Autor> listadoAutores = autorService.listarAutores();
                listadoAutores.forEach(a -> logger.info(a.getNombre() + sl));
                logger.info("Ingrese el nombre del autor a consultar: " + sl);
                List<Libro> libros = autorService.listarLibrosPorAutor(consola.nextLine());
                if (libros != null && !libros.isEmpty()) {
                    libros.forEach(l -> logger.info(l.toString() + sl));
                } else {
                    logger.info("No se encontraron libros para este autor." + sl);
                }
            }
            case 7 -> {
                return true;
            }
            default -> logger.info("Numero incorrecto");
        }
        return false;
    }
}