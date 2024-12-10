package com.eternal.api.Service;

import com.eternal.api.Models.Balance;
import com.eternal.api.Models.DTO.Response.DetallesBalance;
import com.eternal.api.Models.DTO.Response.DetallesProducto;
import com.eternal.api.Models.Producto;
import com.eternal.api.Repository.IBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {
    private IBalanceRepository balanceRepository;

    public BalanceService(@Autowired IBalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;

    }
    public void guardarBalance(Balance balance) {
        balanceRepository.save(balance);
    }
    public Optional<Balance> obtenerUltimoBalance() {
        return balanceRepository.findTopByOrderByBalanceIdDesc();
    }
    public DetallesBalance obtenerDetallesBalance() {
        Optional<Balance> balance = balanceRepository.findTopByOrderByBalanceIdDesc();
        if (balance.isPresent()) {
            return new DetallesBalance(balance.get());
        }else {
            return null;
        }
    }

}
