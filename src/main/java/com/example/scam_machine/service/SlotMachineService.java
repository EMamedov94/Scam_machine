package com.example.scam_machine.service;

import com.example.scam_machine.models.Symbol;
import com.example.scam_machine.repository.PlayerRepository;
import com.example.scam_machine.repository.SlotsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SlotMachineService {
    private final PlayerRepository playerRepository;
    private final SlotsRepository slotsRepository;

    // Spin
    public List<Symbol> spin(UserDetails player) {
        var playerDb = playerRepository.findByUsername(player.getUsername());
        var slots = slotsRepository.getReferenceById(1L).getSymbolList();

        Random random = new Random();
        List<Symbol> spinLine = List.of(
                slots.get(random.nextInt(5) + 1),
                slots.get(random.nextInt(5) + 1),
                slots.get(random.nextInt(5) + 1)
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
