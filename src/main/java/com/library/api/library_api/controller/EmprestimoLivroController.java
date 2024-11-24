package com.library.api.library_api.controller;

import com.library.api.library_api.service.EmprestimoLivroService;
import com.library.api.library_api.service.dto.EmprestimoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/emprestimo")
public class EmprestimoLivroController {

    @Autowired
    private EmprestimoLivroService emprestimoLivroService;

    @PostMapping(path = "/solicitar-emprestimo")
    @ResponseStatus(HttpStatus.CREATED)
    public void alugarLivro(@RequestBody EmprestimoRequest request){
        emprestimoLivroService.alugarLivro(request.getUsername(), request.getLivroId());
    }

    @DeleteMapping(path = "/devolver-emprestimo")
    @ResponseStatus(HttpStatus.OK)
    public void devolverLivro(@RequestBody EmprestimoRequest request){
        emprestimoLivroService.devolverLivro(request.getUsername(), request.getLivroId());
    }
}
