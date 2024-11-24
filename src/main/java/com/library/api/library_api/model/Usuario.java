package com.library.api.library_api.model;

import com.library.api.library_api.model.enums.Permissao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loginId;

    @Column(unique = true, name = "login", updatable = false)
    private String username;

    @Column(name = "senha")
    private String password;

    @Column(name = "permissao_usuario")
    private String permissao;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<EmprestimoLivro> emprestimos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        setPermissao(Permissao.USER.getRole());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
