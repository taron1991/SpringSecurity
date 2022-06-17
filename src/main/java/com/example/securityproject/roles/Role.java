package com.example.securityproject.roles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    /*Теперь у каждой роли есть SET permissions (чтобы много раз не писать в конфиге roles)*/
    ADMIN(Set.of(Permisson.DEV_READ,Permisson.DEV_WRITE)),
    USER(Set.of(Permisson.DEV_READ));

    private  final Set<Permisson> permissonSet;

     Role(Set<Permisson> managerWrite) {
         this.permissonSet = managerWrite;
    }

    public Set<Permisson> getPermissonSet() {
        return permissonSet;
    }

    /*класс который решает кто к чему имеет доступ*/
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
         return getPermissonSet()
                 .stream().map(permisson ->  new SimpleGrantedAuthority(permisson.getPermission()))
                 .collect(Collectors.toSet());
    }
}
