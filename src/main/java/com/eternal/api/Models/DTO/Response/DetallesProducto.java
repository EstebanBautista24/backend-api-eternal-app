package com.eternal.api.Models.DTO.Response;

import com.eternal.api.Models.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetallesProducto {

    private Long productoId;
    private String nombre;
    private String descripcion;
    private Double valor;
    private Double precioEnvio;
    private Double total;
    private LocalDate fechaVenta;
    private Double valorVenta;
    private Double ganancia;

    public DetallesProducto(Producto producto) {
        this.productoId = producto.getProductoId();
        this.nombre = producto.getNombre();
        this.valor = producto.getPrecio();
        this.precioEnvio = producto.getPrecioEnvio();
        this.total = producto.getTotal();
        if(producto.getVenta()!=null){
            this.fechaVenta = producto.getVenta().getFecha();
            this.valorVenta = producto.getVenta().getValor();
            this.ganancia = producto.getVenta().getGanancia();
        }
    }
}
