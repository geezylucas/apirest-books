package com.company.books.app.service;

import org.springframework.http.ResponseEntity;

import com.company.books.app.model.Categoria;
import com.company.books.app.response.CategoriaResponseRest;

public interface ICategoriaService {
    ResponseEntity<CategoriaResponseRest> buscarCategorias();

    ResponseEntity<CategoriaResponseRest> buscarCategoriasPorId(Long id);

    ResponseEntity<CategoriaResponseRest> guardarCategoria(Categoria newCategoria);

    ResponseEntity<CategoriaResponseRest> actualizarCategoria(Long id, Categoria newCategoria);

    ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id);
}
