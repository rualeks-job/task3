package ru.aleksey.NauJava.unit;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.aleksey.NauJava.objects.Role;
import ru.aleksey.NauJava.objects.User;
import ru.aleksey.NauJava.repositories.UserRepository;
import ru.aleksey.NauJava.services.MyUserDetailService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MyUserDetailServiceTest {


    @Test
    void addUserPositiveTest() {
        String userLogin = "testUser";
        String userPassword = "password";
        String encodedPassword = "encodedPassword";

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        MyUserDetailService userService = new MyUserDetailService(userRepository, passwordEncoder);


        Mockito.when(userRepository.findByUsername(userLogin)).thenReturn(null);
        Mockito.when(passwordEncoder.encode(userPassword)).thenReturn(encodedPassword);

        User createdUser = userService.addUser(userLogin, userPassword);

        assertNotNull(createdUser);
        assertEquals(userLogin, createdUser.getUsername());
        assertEquals(encodedPassword, createdUser.getPassword());
        assertEquals(Set.of(Role.USER), createdUser.getRoles());

        Mockito.verify(userRepository, times(1)).findByUsername(userLogin);
        Mockito.verify(passwordEncoder, times(1)).encode(userPassword);
    }

    @Test
    void addUserNegativeTest() {
        String userLogin = "existingUser";
        String userPassword = "password";

        User existingUser = new User();
        existingUser.setUsername(userLogin);
        existingUser.setPassword("someEncodedPassword");
        existingUser.setRoles(Set.of(Role.USER));

        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        MyUserDetailService myUserDetailService = new MyUserDetailService(userRepository, passwordEncoder);  // Инъекция зависимостей через конструктор

        Mockito.when(userRepository.findByUsername(userLogin)).thenReturn(existingUser);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            myUserDetailService.addUser(userLogin, userPassword);
        });

        assertEquals("User already exist", exception.getMessage());

        Mockito.verify(userRepository, times(1)).findByUsername(userLogin);
        Mockito.verify(passwordEncoder, never()).encode(anyString());
    }
}
