package com.example.securityproject.securityconfig;

import com.example.securityproject.roles.Permisson;
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
public class ConfigurationWithAuthorities /*extends WebSecurityConfigurerAdapter*/ {

    /*@Override//разграничение доступа
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().
                authorizeRequests()// к какаим страницам пользак кто имеет доступ
                .antMatchers("/").permitAll() //все имеют доступ с начинающим урлом /
                .antMatchers(HttpMethod.GET, "/rest/cont/**").hasAuthority(Permisson.DEV_READ.getPermission())
                //все гет методы имеет пермишон READ
                .antMatchers(HttpMethod.POST, "/rest/cont/**").hasAuthority(Permisson.DEV_WRITE.getPermission())
                //все пост методы имеет пермишон WRITE
                .antMatchers(HttpMethod.DELETE, "/rest/cont/**").hasAuthority(Permisson.DEV_WRITE.getPermission())
                //все делете методы имеет пермишон WRITE
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }



    @Bean//(интерфейс) бин хранящий пользовательскую информацию
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(User
                .builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities(Role.ADMIN.grantedAuthorities())
                //админ имеет пермишон write and read т.к grantedauthorities returns 2 пермишона(write and delete)
                .build(),
                User
                        .builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.grantedAuthorities())
                        //user имеет пермишон read т.к grantedauthorities returns 1 пермишон(read)
                        .build());
    }

    @Bean//бин для шифрования данных
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);

    }*/
}
