package com.example.scam_machine.service;

import com.example.scam_machine.enums.RoleEnum;
import com.example.scam_machine.models.Player;
import com.example.scam_machine.repository.PlayerRepository;
import com.example.scam_machine.repository.SlotsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SlotMachineServiceTest {

    private PlayerRepository playerRepository;
    private UserDetails userDetails;
    private SlotMachineService slotMachineService;

    @BeforeEach
    void setup() {
        playerRepository = mock(PlayerRepository.class);
        SlotsRepository slotsRepository = mock(SlotsRepository.class);
        userDetails = mock(UserDetails.class);
        slotMachineService = new SlotMachineService(playerRepository, slotsRepository);

        when(userDetails.getUsername()).thenReturn("player");
    }

    @Test
    void isHasMoney() {
        Double balance = 20D;
        Player playerWithPositiveBalance = new Player(1L, "player", "sfsd", balance, "", RoleEnum.ROLE_USER);

        when(playerRepository.findByUsername("player")).thenReturn(playerWithPositiveBalance);
        when(userDetails.getUsername()).thenReturn("player");

        assertTrue(slotMachineService.isHasMoney(userDetails));
    }

    @Test
    void notEnoughMoney() {
        Double balance = 10D;
        Player playerWithPositiveBalance = new Player(1L, "player", "sfsd", balance, "", RoleEnum.ROLE_USER);

        when(playerRepository.findByUsername("player")).thenReturn(playerWithPositiveBalance);
        when(userDetails.getUsername()).thenReturn("player");

        assertFalse(slotMachineService.isHasMoney(userDetails));
    }

    @Test
    void negativeBalance() {
        Double balance = -20D;
        Player playerWithPositiveBalance = new Player(1L, "player", "sfsd", balance, "", RoleEnum.ROLE_USER);

        when(playerRepository.findByUsername("player")).thenReturn(playerWithPositiveBalance);
        when(userDetails.getUsername()).thenReturn("player");

        assertFalse(slotMachineService.isHasMoney(userDetails));
    }
}