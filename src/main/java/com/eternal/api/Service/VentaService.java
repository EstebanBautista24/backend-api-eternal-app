package com.eternal.api.Service;

import com.eternal.api.Models.DTO.Register.RegistrarPedido;
import com.eternal.api.Models.DTO.Register.RegistrarVenta;
import com.eternal.api.Models.DTO.Update.ActualizarProducto;
import com.eternal.api.Models.DTO.Update.ActualizarVenta;
import com.eternal.api.Models.Producto;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import com.eternal.api.Models.Venta;
import com.eternal.api.Repository.IVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VentaService {
    private IVentaRepository ventaRepository;
    private TransaccionService transaccionService;
    private ProductoService productoService;
    public VentaService(@Autowired IVentaRepository ventaRepository,@Autowired TransaccionService transaccionService,@Autowired ProductoService productoService) {
        this.ventaRepository = ventaRepository;
        this.transaccionService = transaccionService;
        this.productoService = productoService;
    }

    @Transactional
    public void guardarVenta(Long id,RegistrarVenta registrarVenta){
        Venta venta = new Venta(registrarVenta);
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        if(producto.isPresent()){
            venta.setProducto(producto.get());
            producto.get().setVenta(venta);
            producto.get().venderProducto();
            venta.setGanancia(registrarVenta.getValor()-producto.get().getTotal());

        }

        Transaccion transaccion = transaccionService.crearTransaccion(registrarVenta.getValor(),TipoTransaccion.VENTA);
        venta.setTransaccion(transaccion);
        transaccion.setVenta(venta);
        transaccion.setDescripcion("venta de producto: "+producto.get().getNombre());
        transaccionService.guardarTransaccion(transaccion);
        ventaRepository.save(venta);
    }
    public Double obtenerGananciaFecha(LocalDate fechaInicio,LocalDate fechaFin){
        return ventaRepository.gananciaFecha(fechaInicio,fechaFin);
    }
    public Double obtenerGananciaMes(){
        LocalDate fechaInicio = LocalDate.now().withDayOfMonth(1);
        LocalDate fechaFin = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        return ventaRepository.gananciaFecha(fechaInicio,fechaFin);
    }
    public void actualizarVenta(ActualizarVenta actualizarVenta) {
        Optional<Venta> venta= ventaRepository.findById(actualizarVenta.getVentaId());
        if(venta.isPresent()) {
            venta.get().actualizarVenta(actualizarVenta);

            if(actualizarVenta.getValor()!=null ) {
                Transaccion transaccion = transaccionService.crearTransaccion(actualizarVenta.getValor()-venta.get().getValor(), TipoTransaccion.COMPRA);
                transaccion.setDescripcion("Actualizacion venta producto "+venta.get().getProducto().getProductoId());
                venta.get().setGanancia(actualizarVenta.getValor()-venta.get().getProducto().getTotal());
                transaccionService.guardarTransaccion(transaccion);
                }

            }
        }



}
