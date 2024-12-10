package com.eternal.api.Repository;

import com.eternal.api.Models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBalanceRepository  extends JpaRepository<Balance, Long> {
    Optional<Balance> findTopByOrderByBalanceIdDesc();
}
