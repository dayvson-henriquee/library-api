package com.library.api.library_api.controller;

import com.library.api.library_api.model.Usuario;
import com.library.api.library_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarUsuario(@RequestBody Usuario usuarioLogin){
        usuarioService.salvarUsuario(usuarioLogin);
        return "Usuário cadastrado com sucesso!";
    }
}
