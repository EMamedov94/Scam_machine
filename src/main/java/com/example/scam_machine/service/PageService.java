package com.example.scam_machine.service;

import com.example.scam_machine.models.Symbol;
import com.example.scam_machine.repository.SlotsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PageService {
    private final SlotsRepository slotsRepository;

    // Show default play slots
    public List<Symbol> showDefaultPlaySlots() {
        var symbolsDb = slotsRepository.getReferenceById(1L).getSymbolList();

        Random random = new Random();
        return List.of(
                symbolsDb.get(random.nextInt(5) + 1),
                symbolsDb.get(random.nextInt(5) + 1),
                symbolsDb.get(random.nextInt(5) + 1)
        );
    }
}
