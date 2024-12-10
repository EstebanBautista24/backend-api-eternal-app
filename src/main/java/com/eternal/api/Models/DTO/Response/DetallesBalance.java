package com.eternal.api.Models.DTO.Response;

import com.eternal.api.Models.Balance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallesBalance {
    private Double dineroTotal;
    private Double dineroDisponible;
    private LocalDate fecha;
    private Double dineroInvertido;

    public DetallesBalance(Balance balance){
        this.dineroTotal = balance.getDineroTotal();
        this.dineroDisponible = balance.getDineroDisponible();
        this.fecha = balance.getFecha();
        this.dineroInvertido = balance.getDineroInvertido();
    }
}
