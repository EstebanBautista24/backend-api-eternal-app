package com.eternal.api.Models.DTO.Register;

import com.eternal.api.Config.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
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
public class RegistrarPrestamo {
    @NotBlank
    private String prestamoNombre;
    @NotNull
    private Boolean prestado;
    @NotNull
    private LocalDate fecha;
    @NotNull
    @JsonDeserialize(using = CurrencyDeserializer.class)
    public Double valor;
}
