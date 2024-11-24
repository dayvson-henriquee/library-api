package com.library.api.library_api.service;

import com.library.api.library_api.model.Endereco;
import com.library.api.library_api.model.Usuario;
import com.library.api.library_api.repository.EnderecoRepository;
import com.library.api.library_api.repository.UsuarioLoginRepository;
import com.library.api.library_api.service.dto.EnderecoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioLoginRepository usuarioLoginRepository;


    public void cadastrarEnderecoComUsuario(EnderecoRequest request){
        Usuario usuario = usuarioLoginRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuário não encontrado"));

        Endereco endereco = new Endereco();
        endereco.setUsuario(usuario);
        endereco.setLogradouro(request.getLogradouro());
        endereco.setNumero(request.getNumero());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());

        enderecoRepository.save(endereco);
    };
}
