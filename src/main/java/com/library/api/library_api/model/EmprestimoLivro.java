package com.library.api.library_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id", nullable = false) // Define a chave estrangeira no banco
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false) // Define a chave estrangeira no banco
    private Livro livro;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;


    @PrePersist
    public void prePersist(){
        this.dataInicio = LocalDateTime.now(); //data de empréstimo
        this.dataFim = LocalDateTime.now().plusDays(5); //data da devolução
    }
}
