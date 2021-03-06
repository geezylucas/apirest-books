package com.company.books.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.app.model.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
