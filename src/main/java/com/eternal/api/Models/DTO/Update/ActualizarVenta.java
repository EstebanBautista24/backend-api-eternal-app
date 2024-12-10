package com.eternal.api.Models.DTO.Update;

import com.eternal.api.Config.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarVenta {
    @NotNull
    private Long ventaId;
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Double valor;
    private LocalDate fecha;
}
