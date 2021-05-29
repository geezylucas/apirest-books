package com.company.books.app.model.dao;

import com.company.books.app.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    @Query("select u from Usuario u where u.nombreUsuario = ?1")
    Optional<Usuario> findByIdUsuarioV2(String nombreUsuario);
}
