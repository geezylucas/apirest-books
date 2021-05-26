package com.company.books.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.app.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
