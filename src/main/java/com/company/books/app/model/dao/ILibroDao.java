package com.company.books.app.model.dao;

import com.company.books.app.model.Libro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibroDao extends CrudRepository<Libro, Long> {
}
