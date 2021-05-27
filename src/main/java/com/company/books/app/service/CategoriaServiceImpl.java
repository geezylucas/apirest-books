package com.company.books.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.app.model.Categoria;
import com.company.books.app.model.dao.ICategoriaDao;
import com.company.books.app.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        log.info("Inicio método buscarCategorias()");

        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(categorias);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error al consultar categorias: ", e.getMessage());
            e.getStackTrace();

            response.setMetadata("Respuesta nok", "-1", "Error al consultar categorias");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoriasPorId(Long id) {
        log.info("Inicio método buscarCategoriasPorId");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()) {
                categorias.add(categoria.get());
                response.getCategoriaResponse().setCategorias(categorias);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error al consultar categoria");
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al consultar categoria por id: ", e.getMessage());
            e.getStackTrace();

            response.setMetadata("Respuesta nok", "-1", "Error al consultar categoria por Id");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> guardarCategoria(Categoria newCategoria) {
        log.info("Inicio método guardarCategoria");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Categoria categoria = categoriaDao.save(newCategoria);

            categorias.add(categoria);
            response.getCategoriaResponse().setCategorias(categorias);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");

        } catch (Exception e) {
            log.error("Error al guardar categoria: ", e.getMessage());
            e.getStackTrace();

            response.setMetadata("Respuesta nok", "-1", "Error al guardar categorias");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Long id, Categoria newCategoria) {
        log.info("Inicio método actualizarCategoria");

        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> categorias = new ArrayList<>();

        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);

            if (categoria.isPresent()) {
                categoria.map(c -> {
                    c.setNombre(newCategoria.getNombre());
                    c.setDescripcion(newCategoria.getDescripcion());

                    return categoriaDao.save(c);
                });

                categorias.add(categoria.get());
                response.getCategoriaResponse().setCategorias(categorias);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                log.error("Error al actualizar categoria");
                response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error al actualizar categoria: ", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar categoria");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id) {
        log.info("Inicio método eliminarCategoria");

        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            // Eliminamos el registro
            categoriaDao.deleteById(id);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            log.error("Error al eliminar categoria: ", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar categoria");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
