package com.example.scam_machine.controller;

import com.example.scam_machine.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://localhost:4200/")
public class PageController {
    private final PageService pageService;

    // Show default slots game page
    @GetMapping("/showDefaultSlots")
    public ResponseEntity<Object> showGamePage() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(pageService.showDefaultPlaySlots());
    }

    // Show player profile
    @GetMapping("/profile")
    public ResponseEntity<Object> showProfilePage(@AuthenticationPrincipal UserDetails player) {
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(pageService.showPlayerProfile(player));
    }
}
