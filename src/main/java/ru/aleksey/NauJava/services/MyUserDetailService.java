package ru.aleksey.NauJava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.aleksey.NauJava.configurations.SecurityConfiguration;
import ru.aleksey.NauJava.objects.User;
import ru.aleksey.NauJava.repositories.UserRepository;

import java.util.*;

@Component
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final SecurityConfiguration securityConfiguration;

    @Autowired
    public MyUserDetailService(UserRepository userRepository, SecurityConfiguration securityConfiguration) {
        this.userRepository = userRepository;
        this.securityConfiguration = securityConfiguration;
    }

    public User addUser(String userLogin, String userPassword) {
        User user = userRepository.findByUsername(userLogin);
        if (user != null) {
            throw new RuntimeException("User already exist");
        }

        User user1 = new User();
        user1.setUsername(userLogin);
        user1.setPassword(securityConfiguration.passwordEncoder().encode(userPassword));
        user1.setRoles("USER");

        userRepository.save(user1);
        return user1;
    }

    public String getPassword(String password) {
        return password;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username);
        if (appUser != null) {
            return new
                    org.springframework.security.core.userdetails.User(
                    appUser.getUsername(), appUser.getPassword(),
                    mapRoles(appUser));
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    private Collection<? extends GrantedAuthority> mapRoles(User appUser) {
        return Set.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRoles()));
    }
}
