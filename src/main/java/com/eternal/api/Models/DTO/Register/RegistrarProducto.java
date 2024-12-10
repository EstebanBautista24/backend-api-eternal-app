package com.eternal.api.Models.DTO.Register;

import com.eternal.api.Config.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarProducto {
    @NotBlank
    private String nombre;

    @NotNull
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Double precio;
    @NotNull
    private Double precioEnvio;
}
