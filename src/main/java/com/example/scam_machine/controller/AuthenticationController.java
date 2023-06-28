package com.example.scam_machine.controller;

import com.example.scam_machine.models.Player;
import com.example.scam_machine.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://localhost:4200/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginPlayer(@RequestBody Player player,
                                              BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Validation error");
        }
        if (!authenticationService.authenticate(player)) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Wrong username or password");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(authenticationService.loginPlayer(player));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registrationNewPlayer(@RequestBody Player player,
                                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Validation error");
        }
        if (authenticationService.isUsernameExist(player.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username already exist");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(authenticationService.registrationPlayer(player));
    }
}
