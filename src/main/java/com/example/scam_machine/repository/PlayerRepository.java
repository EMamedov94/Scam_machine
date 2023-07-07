package com.example.scam_machine.repository;

import com.example.scam_machine.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
    boolean existsByUsername(String username);
}
