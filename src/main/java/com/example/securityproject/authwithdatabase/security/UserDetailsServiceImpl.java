package com.example.securityproject.authwithdatabase.security;


import com.example.securityproject.models.User;
import com.example.securityproject.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userDetails")
//в этом классе нам уже нужно создать свою реализацию интерфейса UserDetailsService
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        log.info("in the constructor userdetailservice");
        this.userRepository = userRepository;
    }

    //аутентификация по username чаще всего по имени происходит(но не всегда!)
    //тоесть заходим на сайт(регистрируемся) через емайл,система заходит в бд смотрит есть ли такой емайл,
    // если да то заходишь на сайт
    //можем регистрироваться через другие данные таблицы(через username,password)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("in the loadUserByUsername method");
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("not found"));

        //User user1 = userRepository.findByFirst_name(email).orElseThrow(()->new UsernameNotFoundException("noo"));

        return SecurityUser.fromUser(user);
    }

}
