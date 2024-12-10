package com.eternal.api.Models;

import com.eternal.api.Models.DTO.Register.RegistrarPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private Double totalPedido;
    private LocalDate fecha;
    @OneToOne
    @JoinColumn(referencedColumnName = "transaccionId")
    private Transaccion transaccion;

    @OneToMany(mappedBy = "pedido")
    List<Producto> productos;

    public Pedido(RegistrarPedido registrarPedido){
        productos = new ArrayList<>();
        this.totalPedido = registrarPedido.getValor();
        this.fecha = registrarPedido.getFecha();
    }
}
