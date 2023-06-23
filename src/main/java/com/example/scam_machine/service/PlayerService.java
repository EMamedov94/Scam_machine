package com.example.scam_machine.service;

import com.example.scam_machine.models.Player;
import com.example.scam_machine.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    // Cash in balance
//    public void cashInPlayerBalance(UserDetails player, double cash) {
//        var playerDb = playerRepository.findByUsername(player.getUsername());
//        playerDb.setBalance(playerDb.getBalance() + cash);
//        playerRepository.save(playerDb);
//    }
}
