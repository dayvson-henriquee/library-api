package com.library.api.library_api.repository;

import com.library.api.library_api.model.EmprestimoLivro;
import com.library.api.library_api.model.Livro;
import com.library.api.library_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmprestimoLivroRepository extends JpaRepository<EmprestimoLivro, Long> {

    @Modifying
    @Query("DELETE FROM EmprestimoLivro e WHERE e.usuario = :usuario AND e.livro = :livro")
    void deleteByUsuarioAndLivro(@Param("usuario") Usuario usuario, @Param("livro") Livro livro);
}
