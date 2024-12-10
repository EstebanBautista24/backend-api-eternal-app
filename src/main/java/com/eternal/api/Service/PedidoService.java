package com.eternal.api.Service;

import com.eternal.api.Models.DTO.Register.RegistrarPedido;
import com.eternal.api.Models.Pedido;
import com.eternal.api.Models.Producto;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import com.eternal.api.Repository.IPedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class PedidoService {
    private IPedidoRepository pedidoRepository;
    private ProductoService productoService;
    private TransaccionService transaccionService;

    public PedidoService(@Autowired IPedidoRepository pedidoRepository, @Autowired ProductoService productoService, @Autowired TransaccionService transaccionService) {
        this.pedidoRepository = pedidoRepository;
        this.productoService = productoService;
        this.transaccionService = transaccionService;
    }

    @Transactional
    public void guardarPedido(RegistrarPedido registrarPedido) {
        Pedido pedido = new Pedido(registrarPedido);

        List<Producto> productos= registrarPedido.getProductos().stream()
                .map (p -> {
            Producto producto = new Producto(p);
            producto.setPedido(pedido);
            productoService.guardarProducto(producto);
            return producto;
                    }
                )   .collect(Collectors.toCollection(ArrayList::new));;
        Transaccion transaccion = transaccionService.crearTransaccion(registrarPedido.getValor(),TipoTransaccion.COMPRA);
        pedido.setTransaccion(transaccion);
        pedido.setProductos(productos);
        transaccion.setPedido(pedido);
        Pedido pedido1 = pedidoRepository.save(pedido);
        transaccion.setDescripcion("Pedido con id: "+pedido1.getPedidoId());
        transaccionService.guardarTransaccion(transaccion);
    }


}
