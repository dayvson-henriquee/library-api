package com.library.api.library_api.service;

import com.library.api.library_api.model.EmprestimoLivro;
import com.library.api.library_api.model.EmprestimoLog;
import com.library.api.library_api.model.Livro;
import com.library.api.library_api.model.Usuario;
import com.library.api.library_api.repository.EmprestimoLivroRepository;
import com.library.api.library_api.repository.EmprestimoLogRepository;
import com.library.api.library_api.repository.LivroRepository;
import com.library.api.library_api.repository.UsuarioLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmprestimoLivroService {

    @Autowired
    private EmprestimoLivroRepository emprestimoLivroRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioLoginRepository usuarioLoginRepository;

    @Autowired
    private EmprestimoLogRepository logRepository;

    public List<EmprestimoLivro> listarLivrosAlugados(){
        return emprestimoLivroRepository.findAll();
    }

    public void alugarLivro(String username, Long livroId){
        Usuario usuario = usuarioLoginRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));


        //Faz empréstimo do livro para o usuário e registra em banco
        EmprestimoLivro emprestimoLivro = new EmprestimoLivro();
        emprestimoLivro.setLivro(livro);
        emprestimoLivro.setUsuario(usuario);
        emprestimoLivroRepository.save(emprestimoLivro);

        //Atualiza a contagem de empréstimos que o livro fez
        livro.setQuantidadeVezesAlugado(livro.getQuantidadeVezesAlugado() + 1L);
        livroRepository.save(livro);

        //Registra o log do empréstimo para histórico
        EmprestimoLog emprestimoLog = new EmprestimoLog();
        emprestimoLog.setUsuarioId(usuario.getLoginId());
        emprestimoLog.setLivroId(livroId);
        logRepository.save(emprestimoLog);
    }

    @Transactional
    public void devolverLivro(String username, Long livroId) {
        Usuario usuario = usuarioLoginRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));


        //Deleta em banco o empréstimo realizado do livro
        emprestimoLivroRepository.deleteByUsuarioAndLivro(usuario, livro);
    }
}
