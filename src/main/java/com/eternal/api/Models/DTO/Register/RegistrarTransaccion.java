package com.eternal.api.Models.DTO.Register;

import com.eternal.api.Config.CurrencyDeserializer;
import com.eternal.api.Models.TipoTransaccion;
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
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarTransaccion {
    @NotNull
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Double valor;
    @NotBlank
    private String descripcion;
    @NotNull
    private String tipo;
    @NotNull
    private LocalDate fecha;
}
