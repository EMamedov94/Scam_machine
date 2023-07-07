package com.example.scam_machine.service;

import com.example.scam_machine.configuration.JwtService;
import com.example.scam_machine.models.Player;
import com.example.scam_machine.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Test
    void isUsernameExist() {
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        JwtService jwtService = mock(JwtService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        AuthenticationService authenticationService =
                new AuthenticationService(playerRepository, jwtService, passwordEncoder, authenticationManager);

        String existingUsername = "user";
        String nonExistingUsername = "nonexistent";

        // Настройка мок-объекта playerRepository для возврата реальных значений
        when(playerRepository.existsByUsername(existingUsername)).thenReturn(true);
        when(playerRepository.existsByUsername(nonExistingUsername)).thenReturn(false);

        // Проверка, что метод isUsernameExist корректно определяет существующие и несуществующие имена пользователя
        assertTrue(authenticationService.isUsernameExist(existingUsername));
        assertFalse(authenticationService.isUsernameExist(nonExistingUsername));
    }
}