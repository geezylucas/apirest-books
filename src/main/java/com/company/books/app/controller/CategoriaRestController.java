package com.company.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.app.model.Categoria;
import com.company.books.app.response.CategoriaResponseRest;
import com.company.books.app.service.ICategoriaService;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaService categoriaService;

	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultarCategorias() {
		ResponseEntity<CategoriaResponseRest> response = categoriaService.buscarCategorias();

		return response;
	}

	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> consultarCategoriasPorId(@PathVariable Long id) {
		ResponseEntity<CategoriaResponseRest> response = categoriaService.buscarCategoriasPorId(id);

		return response;
	}

	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> creatCategoria(@RequestBody Categoria newCategoria) {
		ResponseEntity<CategoriaResponseRest> response = categoriaService.guardarCategoria(newCategoria);

		return response;
	}

	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> editarCategoria(@PathVariable Long id,
			@RequestBody Categoria newCategoria) {
		ResponseEntity<CategoriaResponseRest> response = categoriaService.actualizarCategoria(id, newCategoria);

		return response;
	}

	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> removerCategorias(@PathVariable Long id) {
		ResponseEntity<CategoriaResponseRest> response = categoriaService.eliminarCategoria(id);

		return response;
	}

}
