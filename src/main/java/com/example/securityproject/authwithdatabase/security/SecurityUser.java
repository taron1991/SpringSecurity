package com.example.securityproject.authwithdatabase.security;

import com.example.securityproject.models.User;
import com.example.securityproject.roles.Status;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Slf4j
//в этом классе мы создаем свою реализацию интерфейса UserDetails т.е наш User
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> simpleGrantedAuthorityList;
    private final boolean isActive;


    public SecurityUser(String username, String password,
                        List<SimpleGrantedAuthority> simpleGrantedAuthorityList,
                        boolean isActive) {
        this.username = username;
        this.password = password;
        this.simpleGrantedAuthorityList = simpleGrantedAuthorityList;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("in the getAuthorities");
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        log.info("in the password");
        return password;
    }

    @Override
    public String getUsername() {
        log.info("in the username");
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        log.info("in the isAccountNonExpired");
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.info("in the isAccountNonLocked");
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.info("in the isCredentialsNonExpired");
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        log.info("in the isEnabled");
        return isActive;
    }

    public static UserDetails fromUser(User user) {
        log.info("in the userdetails");
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getStatus().equals(Status.ACTIVE),
                user.getRole().getGrantedAuthorities());
    }
}
