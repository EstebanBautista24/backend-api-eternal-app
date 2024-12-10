package com.eternal.api.Models;

public enum TipoTransaccion {
    GASTO("Gasto"),
    PRESTAMO("Prestamo"),
    PAGOPRESTAMO("PagoPrestamo"),
    INGRESO("Ingreso"),
    COMPRA("Compra"),
    VENTA("Venta"),
    ENVIO("Envio");
    private String tipo;
public static TipoTransaccion fromString(String text) {
    for (TipoTransaccion tipo : TipoTransaccion.values()) {
        if (tipo.tipo.equalsIgnoreCase(text)) {
            return tipo;
        }
    }
    throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
}
TipoTransaccion(String tipo){
    this.tipo = tipo;
}
}
