package com.library.api.library_api.repository;

import com.library.api.library_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAllByOrderByQuantidadeVezesAlugadoDesc();
}
