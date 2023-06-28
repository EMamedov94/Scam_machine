package com.example.scam_machine.controller;

import com.example.scam_machine.service.SlotMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://localhost:4200/")
public class MachineController {
    private final SlotMachineService machineService;

    @PostMapping("/spin")
    public ResponseEntity<Object> spin(@AuthenticationPrincipal UserDetails player) {
        if (player == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Need auth");
        }
        if (!machineService.isHasMoney(player)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No money");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .body(machineService.spin(player));
    }
}
