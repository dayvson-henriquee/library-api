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
public class EmprestimoLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer usuarioId;

    private Long livroId;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;


    @PrePersist
    public void prePersist(){
        this.dataInicio = LocalDateTime.now(); //data de empréstimo
        this.dataFim = LocalDateTime.now().plusDays(5); //data da devolução
    }
}
