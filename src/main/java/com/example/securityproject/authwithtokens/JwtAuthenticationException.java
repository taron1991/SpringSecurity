package com.example.securityproject.authwithtokens;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    private HttpStatus h;

    public JwtAuthenticationException(String detail) {
        super(detail);
    }

    public JwtAuthenticationException(String detail, Throwable ex) {
        super(detail, ex);
    }

    public JwtAuthenticationException(String detail, HttpStatus h) {
        super(detail);
        this.h = h;
    }

}
