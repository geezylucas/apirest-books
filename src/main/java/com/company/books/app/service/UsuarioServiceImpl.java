package com.company.books.app.service;

import com.company.books.app.model.Usuario;
import com.company.books.app.model.dao.IUsuarioDao;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioDao.findByNombreUsuario(s);

        if (usuario.isEmpty()) {
            log.error("Error, el usuario no existe");
            throw new UsernameNotFoundException("El usuario no existe");
        }
        List<GrantedAuthority> authorities = usuario.get().getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.get().getNombreUsuario(), usuario.get().getPassword(), usuario.get().getHabilitado(), true, true, true, authorities);
    }
}
