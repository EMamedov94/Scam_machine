package com.example.scam_machine.service;

import com.example.scam_machine.models.Player;
import com.example.scam_machine.models.Symbol;
import com.example.scam_machine.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SlotMachineService {
    private final PlayerRepository playerRepository;
    private final List<Symbol> slotsLine = List.of(
            new Symbol("lemon", 1),
            new Symbol("seven", 2),
            new Symbol("strawberry", 3)
    );

    // Spin
    public List<Symbol> spin(UserDetails player) {
        var playerDb = playerRepository.findByUsername(player.getUsername());

        Random random = new Random();
        List<Symbol> spinLine = List.of(
                slotsLine.get(random.nextInt(2) + 1),
                slotsLine.get(random.nextInt(2) + 1),
                slotsLine.get(random.nextInt(2) + 1)
        );

        if (spinLine.stream().allMatch(m -> m.equals(spinLine.get(0)))) {
            playerDb.setBalance(playerDb.getBalance() + 100);
        } else {
            playerDb.setBalance(playerDb.getBalance() - 10);
        }
        playerRepository.save(playerDb);
        return spinLine;
    }

    public boolean isHasMoney(UserDetails player) {
        return playerRepository.findByUsername(player.getUsername()).getBalance() > 0;
    }
}
