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

        return categoriaService.buscarCategorias();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultarCategoriasPorId(@PathVariable Long id) {

        return categoriaService.buscarCategoriasPorId(id);
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> creatCategoria(@RequestBody Categoria newCategoria) {

        return categoriaService.guardarCategoria(newCategoria);
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> editarCategoria(@PathVariable Long id,
                                                                 @RequestBody Categoria newCategoria) {

        return categoriaService.actualizarCategoria(id, newCategoria);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> removerCategorias(@PathVariable Long id) {

        return categoriaService.eliminarCategoria(id);
    }

}
