package com.example.noleggio.repository;

import com.example.noleggio.entities.Noleggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {
}
