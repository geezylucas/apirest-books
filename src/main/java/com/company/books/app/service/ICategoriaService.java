package com.company.books.app.service;

import org.springframework.http.ResponseEntity;

import com.company.books.app.model.Categoria;
import com.company.books.app.response.CategoriaResponseRest;

public interface ICategoriaService {
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();

	public ResponseEntity<CategoriaResponseRest> buscarCategoriasPorId(Long id);

	public ResponseEntity<CategoriaResponseRest> guardarCategoria(Categoria newCategoria);

	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Long id, Categoria newCategoria);

	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id);
}
