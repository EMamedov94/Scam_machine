package com.example.scam_machine.repository;

import com.example.scam_machine.models.Slots;
import com.example.scam_machine.models.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotsRepository extends JpaRepository<Slots, Long> {
    List<Slots> findAllById(int i);
}
