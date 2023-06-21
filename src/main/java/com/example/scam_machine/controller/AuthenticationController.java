package com.example.scam_machine.controller;

import com.example.scam_machine.models.Player;
import com.example.scam_machine.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Player> loginPlayer(@RequestBody Player player) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(authenticationService.loginPlayer(player));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registrationNewPlayer(@RequestBody Player player) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(authenticationService.registrationPlayer(player));
    }
}
