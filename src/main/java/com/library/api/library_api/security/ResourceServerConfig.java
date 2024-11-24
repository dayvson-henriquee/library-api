package com.library.api.library_api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/library/api/usuario/**").permitAll()
                .antMatchers("/library/api/livro/**").authenticated()
                .antMatchers("/library/api/emprestimo/**").authenticated()
                .antMatchers("/library/api/endereco/**").authenticated()
                .anyRequest().denyAll();



    }
}
