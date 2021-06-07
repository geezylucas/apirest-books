package com.company.books.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.app.model.Categoria;
import com.company.books.app.model.dao.ICategoriaDao;
import com.company.books.app.response.CategoriaResponseRest;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements ICategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    private final ICategoriaDao categoriaDao;

    private static final String RESPUESTA_OK = "Respuesta ok";
    private static final String RESPUESTA_OK_MESSAGE = "Respuesta exitosa";
    private static final String RESPUESTA_OK_CODE = "00";

    private static final String RESPUESTA_NOK = "Respuesta nok";
    private static final String RESPUESTA_NOK_CODE = "-1";

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        log.info("Inicio método buscarCategorias()");

        var response = new CategoriaResponseRest();

        try {
            List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(categorias);
            response.setMetadata(RESPUESTA_OK, RESPUESTA_OK_CODE, RESPUESTA_OK_MESSAGE);
        } catch (Exception e) {
            log.error("Error al consultar categorias: {}", e.getMessage());
            e.getStackTrace();

            response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Error al consultar categorias");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoriasPorId(Long id) {
        log.info("Inicio método buscarCategoriasPorId");

        var response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()) {
                categorias.add(categoria.get());
                response.getCategoriaResponse().setCategorias(categorias);
                response.setMetadata(RESPUESTA_OK, RESPUESTA_OK_CODE, RESPUESTA_OK_MESSAGE);
            } else {
                log.error("Error al consultar categoria");
                response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Categoria no encontrada");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al consultar categoria por id: {}", e.getMessage());
            e.getStackTrace();

            response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Error al consultar categoria por Id");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> guardarCategoria(Categoria newCategoria) {
        log.info("Inicio método guardarCategoria");

        var response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            var categoria = categoriaDao.save(newCategoria);

            categorias.add(categoria);
            response.getCategoriaResponse().setCategorias(categorias);
            response.setMetadata(RESPUESTA_OK, RESPUESTA_OK_CODE, RESPUESTA_OK_MESSAGE);
        } catch (Exception e) {
            log.error("Error al guardar categoria: {}", e.getMessage());
            e.getStackTrace();

            response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Error al guardar categorias");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Long id, Categoria newCategoria) {
        log.info("Inicio método actualizarCategoria");

        var response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()) {
                categoria.get().setNombre(newCategoria.getNombre());
                categoria.get().setDescripcion(newCategoria.getDescripcion());

                categoriaDao.save(categoria.get());

                categorias.add(categoria.get());
                response.getCategoriaResponse().setCategorias(categorias);
                response.setMetadata(RESPUESTA_OK, RESPUESTA_OK_CODE, RESPUESTA_OK_MESSAGE);
            } else {
                log.error("Error al actualizar categoria");
                response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Categoria no actualizada");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al actualizar categoria: {}", e.getMessage());
            response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Error al actualizar categoria");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id) {
        log.info("Inicio método eliminarCategoria");

        var response = new CategoriaResponseRest();

        try {
            // Eliminamos el registro
            categoriaDao.deleteById(id);
            response.setMetadata(RESPUESTA_OK, RESPUESTA_OK_CODE, RESPUESTA_OK_MESSAGE);
        } catch (Exception e) {
            log.error("Error al eliminar categoria: {}", e.getMessage());
            response.setMetadata(RESPUESTA_NOK, RESPUESTA_NOK_CODE, "Error al eliminar categoria");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
