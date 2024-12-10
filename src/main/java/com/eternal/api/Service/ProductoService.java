package com.eternal.api.Service;

import com.eternal.api.Models.DTO.Response.DetallesProducto;
import com.eternal.api.Models.DTO.Update.ActualizarProducto;
import com.eternal.api.Models.Producto;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import com.eternal.api.Repository.IProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private IProductoRepository productoRepository;
    private TransaccionService transaccionService;
    public ProductoService(@Autowired IProductoRepository productoRepository, @Lazy TransaccionService transaccionService) {
        this.productoRepository = productoRepository;
        this.transaccionService = transaccionService;
    }
    public List<DetallesProducto> obtenerTodos() {
        return productoRepository.obtenerOrdenado().stream().map(p->new DetallesProducto(p)).toList();
    }
    public List<DetallesProducto> obtenerDisponibles(){
        return productoRepository.findByEstadoFalse().stream().map(p->new DetallesProducto(p)).toList();
    }
    public List<DetallesProducto> obtenerVendidos(){
        return productoRepository.findByEstadoTrue().stream().map(p->new DetallesProducto(p)).toList();
    }
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }
    public Double obtenerDineroInvertido(){
        List<DetallesProducto> productosDisponibles = obtenerDisponibles();
        return  productosDisponibles.stream()
                .mapToDouble(DetallesProducto::getTotal)
                .sum();

    }
    @Transactional
    public void actualizarProducto(Long id,ActualizarProducto actualizarProducto) {
        Optional<Producto> producto= productoRepository.findById(id);
        System.out.println(actualizarProducto);
        System.out.println(producto.get());
        if(producto.isPresent()) {
            if (actualizarProducto.getPrecioEnvio() != null) {
                if (!actualizarProducto.getPrecioEnvio().equals(producto.get().getPrecioEnvio())) {
                    Transaccion transaccion = transaccionService.crearTransaccion(actualizarProducto.getPrecioEnvio() - producto.get().getPrecioEnvio(), TipoTransaccion.ENVIO);
                    transaccion.setDescripcion("Envio de producto " + producto.get().getProductoId());
                    transaccionService.guardarTransaccion(transaccion);
                }

            }
            if (actualizarProducto.getValor() != null) {
                if (!actualizarProducto.getValor().equals(producto.get().getPrecio())) {
                    Transaccion transaccion = transaccionService.crearTransaccion(actualizarProducto.getValor() - producto.get().getPrecio(), TipoTransaccion.COMPRA);
                    transaccion.setDescripcion("Actualizacion precio producto " + producto.get().getProductoId());
                    transaccionService.guardarTransaccion(transaccion);
                }

            }
            Producto productoActual = producto.get();
            System.out.println(actualizarProducto);
            productoActual.actualizarProducto(actualizarProducto);
            System.out.println(productoActual);

        }

    }
    public DetallesProducto obtenerProductoId(Long id) {
        Optional<Producto> producto= productoRepository.findById(id);
        if(producto.isPresent()) {
            return new DetallesProducto(producto.get());
        }else{
            return null;
        }
    }
}
