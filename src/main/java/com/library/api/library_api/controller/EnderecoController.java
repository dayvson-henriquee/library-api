package com.library.api.library_api.controller;

import com.library.api.library_api.service.EnderecoService;
import com.library.api.library_api.service.dto.EnderecoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping(path = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEnderecoComUsuario(@RequestBody EnderecoRequest request){
        enderecoService.cadastrarEnderecoComUsuario(request);
    }
}
