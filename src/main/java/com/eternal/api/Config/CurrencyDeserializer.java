package com.eternal.api.Config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class CurrencyDeserializer extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            return null;
        }
        // Eliminar el símbolo de dólar, comas y espacios en blanco
        value = value.replaceAll("[$,\\s]", "");

        try {
            // Convertir a Double, asumiendo que el punto es el separador decimal
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            // Manejo del caso de formato incorrecto
            throw new IOException("No se pudo deserializar el valor como Double: " + value, e);
        }
    }
}