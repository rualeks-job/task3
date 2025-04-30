package ru.aleksey.NauJava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.aleksey.NauJava.objects.Role;
import ru.aleksey.NauJava.objects.User;
import ru.aleksey.NauJava.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public MyUserDetailService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(String userLogin, String userPassword) {
        User user = userRepository.findByUsername(userLogin);
        if (user != null) {
            throw new RuntimeException("User already exist");
        }

        User user1 = new User();
        user1.setUsername(userLogin);
        user1.setPassword(getPassword(userPassword));
        user1.setRoles(Set.of(Role.USER));

        userRepository.save(user1);
        return user1;
    }

    public String getPassword(String password) {
        return passwordEncoder.encode(password);
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
        return appUser.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList());
    }
}
