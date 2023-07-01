package com.example.scam_machine.service;

import com.example.scam_machine.models.Player;
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
public class PageService {
    private final SlotsRepository slotsRepository;
    private final PlayerRepository playerRepository;

    // Show default play slots
    public List<Symbol> showDefaultPlaySlots() {
        return slotsRepository.getReferenceById(1L).getSymbolList();
    }

    // Show profile page
    public Player showPlayerProfile(UserDetails player) {
        return playerRepository.findByUsername(player.getUsername());
    }
}
