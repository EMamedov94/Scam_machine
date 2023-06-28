package com.example.scam_machine.controller;

import com.example.scam_machine.models.Player;
import com.example.scam_machine.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://localhost:4200/")
public class PlayerController {
    private final PlayerService playerService;

    // Get current user
    @GetMapping("/getCurrentUser")
    public ResponseEntity<Player> getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playerService.getCurrentUser(user));
    }
}
