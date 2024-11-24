package com.library.api.library_api.controller;

import com.library.api.library_api.model.Livro;
import com.library.api.library_api.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/api/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping(path = "/buscar/livro/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Livro buscarLivroPorId(@PathVariable Long id){
        return livroService.buscarLivroPorId(id);
    }

    @GetMapping(path = "/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Livro> listarLivros(){
        return livroService.listarLivros();
    }

    @PostMapping(path = "/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarLivro(@RequestBody Livro livro){
        livroService.criarLivro(livro);
    }

    @GetMapping(path = "/lista-livros-mais-alugados")
    public List<Livro> findAllByOrderByQuantidadeVezesAlugadoDesc(){
        return livroService.findAllByOrderByQuantidadeVezesAlugadoDesc();
    }
}
