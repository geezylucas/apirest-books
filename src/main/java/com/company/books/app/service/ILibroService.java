package com.company.books.app.service;

import com.company.books.app.model.Libro;
import com.company.books.app.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {
    ResponseEntity<LibroResponseRest> buscarLibros();

    ResponseEntity<LibroResponseRest> buscarLibrosPorId(Long id);

    ResponseEntity<LibroResponseRest> guardarLibro(Libro newLibro);

    ResponseEntity<LibroResponseRest> actualizarLibro(Long id, Libro newLibro);

    ResponseEntity<LibroResponseRest> eliminarLibro(Long id);
}
