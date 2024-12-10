package com.eternal.api.Models;

import com.eternal.api.Models.DTO.Register.RegistrarProducto;
import com.eternal.api.Models.DTO.Update.ActualizarProducto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String nombre;
    private Double precio;
    private boolean estado;

    private Double precioEnvio;
    private Double total;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "pedidoId")
    private Pedido pedido;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "ventaId")
    private Venta venta;

    public Producto(RegistrarProducto registrarProducto){
        this.nombre = registrarProducto.getNombre();

        this.precio = registrarProducto.getPrecio();
        this.total = registrarProducto.getPrecio()+registrarProducto.getPrecioEnvio()   ;
        this.estado = false;
        this.precioEnvio = registrarProducto.getPrecioEnvio();

    }

    public void venderProducto(){
        this.estado = true;
    }
    public void actualizarProducto(ActualizarProducto actualizarProducto){
        if(actualizarProducto.getNombre()!=null ){
            this.nombre = actualizarProducto.getNombre();
        }
         if(actualizarProducto.getValor()!=null ){
            this.precio = actualizarProducto.getValor();
            this.total = actualizarProducto.getValor() + this.precioEnvio;
        } if(actualizarProducto.getPrecioEnvio()!=null){
            this.precioEnvio = actualizarProducto.getPrecioEnvio();
            this.total = this.precio+actualizarProducto.getPrecioEnvio();
        }

    }
}
