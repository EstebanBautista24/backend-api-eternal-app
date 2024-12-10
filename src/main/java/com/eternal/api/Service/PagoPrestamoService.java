package com.eternal.api.Service;

import com.eternal.api.Models.DTO.Register.RegistrarPagoPrestamo;
import com.eternal.api.Models.PagoPrestamo;
import com.eternal.api.Models.Prestamo;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import com.eternal.api.Repository.IPagoPrestamoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagoPrestamoService {
    private IPagoPrestamoRepository pagoPrestamoRepository;
    private PrestamoService prestamoService;
    private TransaccionService transaccionService;
    public PagoPrestamoService(@Autowired IPagoPrestamoRepository pagoPrestamoRepository,@Autowired PrestamoService prestamoService,@Autowired TransaccionService transaccionService) {
        this.pagoPrestamoRepository = pagoPrestamoRepository;
        this.prestamoService = prestamoService;
        this.transaccionService = transaccionService;
    }
    @Transactional
    public void guardarPagoPrestamo(Long id,RegistrarPagoPrestamo registrarPagoPrestamo){
        PagoPrestamo pagoPrestamo = new PagoPrestamo(registrarPagoPrestamo);

        Optional<Prestamo> prestamoOptional = prestamoService.obtenerPrestamoPorId(id);
        if(prestamoOptional.isPresent()){
            Prestamo prestamo = prestamoOptional.get();
            pagoPrestamo.setPrestamo(prestamo);
            prestamo.getPagos().add(pagoPrestamo);
            if(registrarPagoPrestamo.getValor()<=prestamo.getDeudaTotal()){
                prestamo.setDeudaTotal(prestamo.getDeudaTotal()-registrarPagoPrestamo.getValor());
                if(prestamo.getDeudaTotal()==0){
                    prestamo.setEstado(false);
                }
            }else{
                throw new ValidationException("El valor no puede ser mayor a la deuda total");
            }
            Transaccion transaccion = transaccionService.crearTransaccion(registrarPagoPrestamo.getValor(), TipoTransaccion.PAGOPRESTAMO);

            transaccion.setPrestamo(prestamo);
            transaccionService.guardarTransaccion(transaccion);
            pagoPrestamoRepository.save(pagoPrestamo);
        }

    }
}
