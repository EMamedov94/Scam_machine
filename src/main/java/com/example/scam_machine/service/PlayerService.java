package com.example.scam_machine.service;

import com.example.scam_machine.models.Player;
import com.example.scam_machine.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    // Get current user
    public Player getCurrentUser(UserDetails user) {
        return playerRepository.findByUsername(user.getUsername());
    }
}
