package com.eternal.api.Service;

import com.eternal.api.Models.DTO.Register.RegistrarPrestamo;
import com.eternal.api.Models.DTO.Response.DetallesPrestamo;
import com.eternal.api.Models.DTO.Update.ActualizarPrestamo;
import com.eternal.api.Models.Pedido;
import com.eternal.api.Models.Prestamo;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import com.eternal.api.Repository.IPrestamoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {
    private IPrestamoRepository prestamoRepository;
    private TransaccionService transaccionService;
    public PrestamoService(@Autowired IPrestamoRepository prestamoRepository,@Autowired TransaccionService transaccionService) {
        this.prestamoRepository = prestamoRepository;
        this.transaccionService = transaccionService;
    }
    @Transactional
    public void guardarPrestamo(RegistrarPrestamo registrarPrestamo) {
        Prestamo prestamo = new Prestamo(registrarPrestamo);
        Transaccion transaccion = transaccionService.crearTransaccion(registrarPrestamo.getValor(), TipoTransaccion.PRESTAMO);

        transaccion.setPrestamo(prestamo);
        prestamo.setTransaccion(transaccion);

        transaccion.setDescripcion("prestamo a: "+prestamo.getPrestamoNombre());
        prestamoRepository.save(prestamo);
        transaccionService.guardarTransaccion(transaccion);
    }
    public Optional<Prestamo> obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id);
    }
    public List<DetallesPrestamo> obtenerPrestamosActivos(){
        return prestamoRepository.findByEstadoTrue().stream().map(p->new DetallesPrestamo(p)).toList();
    }
    public List<DetallesPrestamo> obtenerTodos(){
        return prestamoRepository.obtenerOrdenado().stream().map(p->new DetallesPrestamo(p)).toList();
    }
    public List<DetallesPrestamo> obtenerPrestamosInactivos(){
        return prestamoRepository.findByEstadoFalse().stream().map(p->new DetallesPrestamo(p)).toList();
    }
    public DetallesPrestamo obtenerPrestamoId(Long id){
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(prestamo.isPresent()){
            return new DetallesPrestamo(prestamo.get());

        }
        return null;
    }

    @Transactional
    public void actualizarPrestamo(Long id, ActualizarPrestamo actualizarPrestamo){
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(prestamo.isPresent()){
            Transaccion transaccion = transaccionService.crearTransaccion(Math.abs(actualizarPrestamo.getPrestamoValor()-prestamo.get().getPrestamoValor()), TipoTransaccion.PRESTAMO);
            transaccion.setPrestamo(prestamo.get());
            transaccion.setDescripcion("actualizacion prestamo " + prestamo.get().getPrestamoId());

            transaccionService.guardarTransaccion(transaccion);
            prestamo.get().actualizar(actualizarPrestamo);
        }
    }
}
