package com.eternal.api.Models;

import com.eternal.api.Models.DTO.Register.RegistrarTransaccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaccionId;
    private LocalDate fecha;
    private Double valor;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;
    @OneToOne(mappedBy = "transaccion")
    private Prestamo prestamo;
    @OneToOne(mappedBy = "transaccion")
    private Venta venta;
    @OneToOne(mappedBy = "transaccion")
    private Pedido pedido;
    @OneToOne(mappedBy = "transaccion")
    private Balance balance;

    public Transaccion(RegistrarTransaccion registrarTransaccion){
        this.valor = registrarTransaccion.getValor();
        this.descripcion = registrarTransaccion.getDescripcion();
        this.tipo = TipoTransaccion.fromString(registrarTransaccion.getTipo());
        this.fecha = registrarTransaccion.getFecha();
    }
}
