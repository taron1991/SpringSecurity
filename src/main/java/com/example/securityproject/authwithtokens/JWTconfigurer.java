package com.example.securityproject.authwithtokens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

//класс для конфигурации токена
@Component
public class JWTconfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JWTfilterToken jwTfilterToken;


    public JWTconfigurer(JWTfilterToken jwTfilterToken) {
        this.jwTfilterToken = jwTfilterToken;
    }


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        builder.addFilterBefore(jwTfilterToken, UsernamePasswordAuthenticationFilter.class);
    }
}
