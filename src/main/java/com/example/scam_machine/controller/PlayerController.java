package com.example.scam_machine.controller;

import com.example.scam_machine.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    // Cash in balance
//    @PostMapping("/cashInBalance")
//    public ResponseEntity<?> cashInBalance(@AuthenticationPrincipal UserDetails player,
//                                                @RequestBody Double cash) {
//        playerService.cashInPlayerBalance(player, cash);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
