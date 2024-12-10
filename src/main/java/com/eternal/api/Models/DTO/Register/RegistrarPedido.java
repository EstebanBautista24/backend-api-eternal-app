package com.eternal.api.Models.DTO.Register;

import com.eternal.api.Config.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarPedido {
    @NotNull
    private List<RegistrarProducto> productos;
    @NotNull
    private LocalDate fecha;
    @NotNull
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Double valor;
}
