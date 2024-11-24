package com.library.api.library_api.service;

import com.library.api.library_api.model.Usuario;
import com.library.api.library_api.repository.UsuarioLoginRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioLoginRepository usuarioLoginRepository;

    public UsuarioService(UsuarioLoginRepository usuarioLoginRepository){
        this.usuarioLoginRepository = usuarioLoginRepository;
    }

    public void salvarUsuario(Usuario usuario){
        boolean loginExistente = usuarioLoginRepository.existsByUsername(usuario.getUsername());

        if( loginExistente ){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Login já cadastrado");
        } else {
            usuarioLoginRepository.save(usuario);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuarioLogin = usuarioLoginRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User
                .builder()
                .username(usuarioLogin.getUsername())
                .password(usuarioLogin.getPassword())
                .roles(usuarioLogin.getPermissao().toUpperCase())
                .build();
    }

}
