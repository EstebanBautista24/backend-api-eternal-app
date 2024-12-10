package com.eternal.api.Models.DTO.Response;

import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallesTransaccion {
    private Long id;
    private LocalDate fecha;
    private Double valor;
    private String descripcion;
    private TipoTransaccion tipo;

public DetallesTransaccion(Transaccion transaccion) {
    this.id = transaccion.getTransaccionId();
    this.fecha = transaccion.getFecha();
    this.valor = transaccion.getValor();
    this.descripcion = transaccion.getDescripcion();
    this.tipo = transaccion.getTipo();

}
}
