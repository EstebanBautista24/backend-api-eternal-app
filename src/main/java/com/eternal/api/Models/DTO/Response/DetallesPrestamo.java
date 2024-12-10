package com.eternal.api.Models.DTO.Response;

import com.eternal.api.Models.Prestamo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallesPrestamo {
    private Long prestamoId;
    private LocalDate fecha;
    private Boolean estado;
    private String prestamoNombre;
    private Boolean prestado;
    private Double prestamoValor;
    private Double deudaTotal;
    public DetallesPrestamo(Prestamo prestamo) {
        this.prestamoId = prestamo.getPrestamoId();
        this.fecha = prestamo.getFecha();
        this.prestamoNombre = prestamo.getPrestamoNombre();
        this.prestado = prestamo.getPrestado();
        this.deudaTotal = prestamo.getDeudaTotal();
        this.prestamoValor = prestamo.getPrestamoValor();
        this.estado = prestamo.getEstado();
    }
}
