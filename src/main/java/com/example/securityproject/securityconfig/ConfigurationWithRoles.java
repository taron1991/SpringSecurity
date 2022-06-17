package com.example.securityproject.securityconfig;

import com.example.securityproject.roles.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
//@EnableWebSecurity
public class ConfigurationWithRoles /*extends WebSecurityConfigurerAdapter*/ {

   /*//разграничение доступа
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().
                authorizeRequests()// к какаим страницам пользак кто имеет доступ
                .antMatchers("/").permitAll() //все имеют доступ с начинающим урлом /
                .antMatchers(HttpMethod.GET, "/rest/cont/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
       *//* на все GET методы имеют доступ и админы и пользователи*//*
                .antMatchers(HttpMethod.POST, "/rest/cont/**").hasRole(Role.ADMIN.name())
       *//* на POST изменение данных имеет доступ только админ*//*
                .antMatchers(HttpMethod.DELETE, "/rest/cont/**").hasRole(Role.ADMIN.name())
                .anyRequest()*//*каждый запрос должен быть *//*
                .authenticated()*//*аутентифицирован*//*
                .and()
                .httpBasic();
    }

    @Bean//(интерфейс) бин хранящий пользовательскую информацию
    @Override
    protected UserDetailsService userDetailsService() {
        return new *//*одна из реализаций интерфейса если мы не работаем с БД*//* InMemoryUserDetailsManager(User
                .builder()
                .username("admin")
                .password(passwordEncoders().encode("admin"))*//*bcrypt-generator.com сайт шифрует данные "админ" будет зашифрован*//*
                .roles(Role.ADMIN.name())
                .build(),
                User
                        .builder()
                        .username("user")
                        .password(passwordEncoders().encode("user"))
                        .roles(Role.USER.name())//user,admin
                        .build());

    }



    @Bean//бин для шифрования данных
    protected PasswordEncoder passwordEncoders() {
        return new BCryptPasswordEncoder(12);

    }*/
}
