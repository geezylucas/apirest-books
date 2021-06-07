package com.company.books.app.service;

import com.company.books.app.model.Libro;
import com.company.books.app.model.dao.ILibroDao;
import com.company.books.app.response.LibroResponseRest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements ILibroService {

    private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);

    private final ILibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibros() {
        log.info("Inicio método buscarLibros");

        LibroResponseRest response = new LibroResponseRest();

        try {
            List<Libro> libros = (List<Libro>) libroDao.findAll();
            response.getLibroResponse().setLibros(libros);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error al consultar libros: ", e.getMessage());
            e.getStackTrace();

            response.setMetadata("Respuesta nok", "-1", "Error al consultar libros");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> buscarLibrosPorId(Long id) {
        log.info("Inicio método buscarLibrosPorId");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> libros = new ArrayList<>();

        try {
            Optional<Libro> libro = libroDao.findById(id);

            if (libro.isPresent()) {
                libros.add(libro.get());
                response.getLibroResponse().setLibros(libros);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error al consultar libro");
                response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al consultar libro: ", e.getMessage());
            e.getStackTrace();

            response.setMetadata("Respuesta nok", "-1", "Error al consultar libro");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> guardarLibro(Libro newLibro) {
        log.info("Inicio método guardarLibro");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> libros = new ArrayList<>();

        try {
            Libro libro = libroDao.save(newLibro);

            libros.add(libro);

            response.getLibroResponse().setLibros(libros);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error al guardar libro: ", e.getMessage());
            e.getStackTrace();

            response.setMetadata("Respuesta nok", "-1", "Error al guardar libro");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> actualizarLibro(Long id, Libro newLibro) {
        log.info("Inicio método actualizarLibro");

        LibroResponseRest response = new LibroResponseRest();
        List<Libro> libros = new ArrayList<>();

        try {
            Optional<Libro> libro = libroDao.findById(id);

            if (libro.isPresent()) {
                libro.map(l -> {
                    l.setNombre(newLibro.getNombre());
                    l.setDescripcion(newLibro.getDescripcion());
                    l.setCategoria(newLibro.getCategoria());

                    return libroDao.save(l);
                });

                libros.add(libro.get());
                response.getLibroResponse().setLibros(libros);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error al actualizar libro");
                response.setMetadata("Respuesta nok", "-1", "Libro no actualizado");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error al actualizar libro: ", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar libro");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LibroResponseRest> eliminarLibro(Long id) {
        log.info("Inicio método eliminarLibro");

        LibroResponseRest response = new LibroResponseRest();

        try {
            libroDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error al eliminar libro: ", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar libro");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}