package com.company.books.app.response;

import com.company.books.app.model.Libro;

import java.util.List;

public class LibroResponse {
    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
