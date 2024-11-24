package com.library.api.library_api.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoRequest {

    private String username;

    private String logradouro;

    private Integer numero;

    private String bairro;

    private String cidade;
}

