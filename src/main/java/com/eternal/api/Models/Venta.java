package com.eternal.api.Models;

import com.eternal.api.Models.DTO.Register.RegistrarVenta;
import com.eternal.api.Models.DTO.Update.ActualizarVenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;
    private Double valor;
    private Double ganancia;
    private LocalDate fecha;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "transaccionId")
    private Transaccion transaccion;
    @OneToOne(mappedBy = "venta")
    private Producto producto;

    public Venta(RegistrarVenta registrarVenta) {
        this.valor = registrarVenta.getValor();
        this.fecha = registrarVenta.getFecha();

    }
    public void actualizarVenta(ActualizarVenta actualizarVenta) {
        if(actualizarVenta.getValor() != null ){
            this.valor = actualizarVenta.getValor();
        } else if (actualizarVenta.getFecha()!= null) {
            this.fecha = actualizarVenta.getFecha();
        }
    }
}
