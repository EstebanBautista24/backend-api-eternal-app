package com.eternal.api.Models.DTO.Update;

import com.eternal.api.Config.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActualizarProducto {
    private String nombre;
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Double valor;
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Double precioEnvio;
}
