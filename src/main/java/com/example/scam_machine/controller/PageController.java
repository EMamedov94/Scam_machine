package com.example.scam_machine.controller;

import com.example.scam_machine.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {
    private final PageService pageService;

    @GetMapping("/gamePage")
    public ResponseEntity<Object> showGamePage() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageService.showDefaultPlaySlots());
    }
}
