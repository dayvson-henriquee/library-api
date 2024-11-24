package com.library.api.library_api.service;

import com.library.api.library_api.model.Livro;
import com.library.api.library_api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro buscarLivroPorId(Long id){
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    public List<Livro> listarLivros(){
        return livroRepository.findAll();
    }

    public void criarLivro(Livro request){
        Livro livro = new Livro();
        livro.setAutor(request.getAutor());
        livro.setTitulo(request.getTitulo());
        livro.setGenero(request.getGenero());
        livro.setAnoPublicacao(request.getAnoPublicacao());
        livro.setQuantidadeVezesAlugado(0L);

        livroRepository.save(livro);
    }

    public List<Livro> findAllByOrderByQuantidadeVezesAlugadoDesc(){
        return livroRepository.findAllByOrderByQuantidadeVezesAlugadoDesc();
    }
}
