package com.company.books.app.controller;

import com.company.books.app.model.Libro;
import com.company.books.app.response.LibroResponseRest;
import com.company.books.app.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LibroRestController {

    @Autowired
    private ILibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> consultarLibros() {

        return libroService.buscarLibros();
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> consultarLibroPorId(@PathVariable Long id) {

        return libroService.buscarLibrosPorId(id);
    }

    @PostMapping("/libros")
    public ResponseEntity<LibroResponseRest> crearLibro(@RequestBody Libro newLibro) {

        return libroService.guardarLibro(newLibro);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> editarLibro(@PathVariable Long id, @RequestBody Libro newLibro) {

        return libroService.actualizarLibro(id, newLibro);
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> removerLibro(@PathVariable Long id) {

        return libroService.eliminarLibro(id);
    }
}
