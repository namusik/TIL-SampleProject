package com.example.exchangerate.repository;

import com.example.exchangerate.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Optional<Exchange> findByCurUnit(String unit);
}
