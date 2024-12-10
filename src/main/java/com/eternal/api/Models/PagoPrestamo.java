package com.eternal.api.Models;

import com.eternal.api.Models.DTO.Register.RegistrarPagoPrestamo;
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
public class PagoPrestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pagoPrestamoId;
    private LocalDate fecha;
    private Double valor;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "prestamoId")
    private Prestamo prestamo;

    public PagoPrestamo(RegistrarPagoPrestamo registrarPagoPrestamo){
        this.fecha = registrarPagoPrestamo.getFecha();
        this.valor = registrarPagoPrestamo.getValor();

    }
}
