package com.example.scam_machine.service;

import com.example.scam_machine.configuration.JwtService;
import com.example.scam_machine.enums.RoleEnum;
import com.example.scam_machine.models.Player;
import com.example.scam_machine.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PlayerRepository playerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Login
    public Player loginPlayer(Player player) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        player.getUsername(),
                        player.getPassword()
                )
        );
        var user = playerRepository.findByUsername(player.getUsername());
        var jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        return user;
    }

    // Registration
    public Player registrationPlayer(Player player) {
        var newPlayer = Player.builder()
                .username(player.getUsername())
                .password(passwordEncoder.encode(player.getPassword()))
                .role(RoleEnum.ROLE_USER)
                .balance(30D)
                .build();
        return playerRepository.save(newPlayer);
    }

    // Check username or password
    public boolean authenticate(Player player) {
        var playerDb = playerRepository.findByUsername(player.getUsername());
        if (playerDb == null) {
            return false;
        }
        return passwordEncoder.matches(player.getPassword(), playerDb.getPassword());
    }

    // Check username exist
    public boolean isUsernameExist(String username) {
        return playerRepository.existsByUsername(username);
    }
}
