package com.example.securityproject.authwithtokens;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//класс-фильтр который будет пропускать через себя запросы
public class JWTfilterToken extends GenericFilterBean {

    private final TokenJWTprovider tokenJWTprovider;

    public JWTfilterToken(TokenJWTprovider tokenJWTprovider) {
        this.tokenJWTprovider = tokenJWTprovider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenJWTprovider.resolveToken((HttpServletRequest) request);

        try {
            if (token != null && tokenJWTprovider.validateToken(token)) {
                Authentication authentication = tokenJWTprovider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            ((HttpServletResponse) response).sendError(e.getH().value());
            throw new JwtAuthenticationException("token is expired or invalid");
        }
        chain.doFilter(request, response);
    }
}