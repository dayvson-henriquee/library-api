package com.library.api.library_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date anoPublicacao;
    private Long quantidadeVezesAlugado;

    @JsonIgnore
    @OneToMany(mappedBy = "livro", orphanRemoval = true)
    private List<EmprestimoLivro> emprestimos = new ArrayList<>();

}
