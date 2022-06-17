package com.example.securityproject.authwithtokens;

import com.example.securityproject.models.User;
import com.example.securityproject.repositories.UserRepository;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestControllerToken {
    private final AuthenticationManager manager;
    private UserRepository userRepository;
    private TokenJWTprovider tokenJWTprovider;

    public RestControllerToken(AuthenticationManager manager, UserRepository userRepository, TokenJWTprovider tokenJWTprovider) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.tokenJWTprovider = tokenJWTprovider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> responseEntity(@RequestBody AuthenticationWithDTO withDTO) {
        try {
            String email = withDTO.getEmail();
            manager.authenticate(new UsernamePasswordAuthenticationToken(withDTO.getEmail(), withDTO.getPassword()));
            User user = userRepository.findByEmail(withDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user doesnt exist"));
            String token = tokenJWTprovider.createToken(withDTO.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", withDTO.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>("token is expired or invalid", HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/logout")
    public void responseEntity(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }
}