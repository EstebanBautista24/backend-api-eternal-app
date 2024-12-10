package com.eternal.api.Service;

import com.eternal.api.Models.Balance;
import com.eternal.api.Models.DTO.Register.RegistrarTransaccion;
import com.eternal.api.Models.DTO.Response.DetallesTransaccion;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Models.Transaccion;
import com.eternal.api.Repository.ITransaccionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {
    private BalanceService balanceService;
    private ITransaccionRepository transaccionRepository;
    private ProductoService productoService;
    public TransaccionService(@Autowired ITransaccionRepository transaccionRepository,@Autowired BalanceService balanceService,@Autowired ProductoService productoService) {
        this.transaccionRepository = transaccionRepository;
        this.balanceService = balanceService;
        this.productoService = productoService;
    }
    @Transactional
    public void guardarTransaccion(Transaccion transaccion) {
        transaccionRepository.save(transaccion);
        actualizarBalance(transaccion);
    }
    public void guardarTransaccion(RegistrarTransaccion registrarTransaccion){
        Transaccion transaccion = new Transaccion(registrarTransaccion);

        transaccionRepository.save(transaccion);
        actualizarBalance(transaccion);
    }

    public void actualizarBalance(Transaccion transaccion){
        Balance balance = new Balance();
        Optional<Balance> balanceOptional = balanceService.obtenerUltimoBalance();
        if(balanceOptional.isPresent()){

            if (transaccion.getTipo().equals(TipoTransaccion.COMPRA)){
                if(transaccion.getValor()>balanceOptional.get().getDineroDisponible()){
                    throw new ValidationException("Excede el valor disponible");
                }
                balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()-transaccion.getValor());
                balance.setDineroInvertido(productoService.obtenerDineroInvertido());
                balance.setDineroTotal(balanceOptional.get().getDineroTotal());
            }
            else if (transaccion.getTipo().equals(TipoTransaccion.VENTA)){
                balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()+transaccion.getValor());
                balance.setDineroInvertido(productoService.obtenerDineroInvertido());
                balance.setDineroTotal(balanceOptional.get().getDineroTotal()+transaccion.getVenta().getGanancia());
            }else if (transaccion.getTipo().equals(TipoTransaccion.PRESTAMO)){
                if(!transaccion.getPrestamo().getPrestado()){
                    balance.setDineroInvertido(balanceOptional.get().getDineroInvertido());
                    balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()+transaccion.getValor());
                    balance.setDineroTotal(balanceOptional.get().getDineroTotal()+transaccion.getValor());
                }else{
                    balance.setDineroInvertido(balanceOptional.get().getDineroInvertido());
                    balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()-transaccion.getValor());
                    balance.setDineroTotal(balanceOptional.get().getDineroTotal()-transaccion.getValor());
                }
            }else if(transaccion.getTipo().equals(TipoTransaccion.PAGOPRESTAMO)){
                if(!transaccion.getPrestamo().getPrestado()){
                    balance.setDineroInvertido(balanceOptional.get().getDineroInvertido());
                    balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()-transaccion.getValor());
                    balance.setDineroTotal(balanceOptional.get().getDineroTotal()-transaccion.getValor());
                }else{
                    balance.setDineroInvertido(balanceOptional.get().getDineroInvertido());
                    balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()+transaccion.getValor());
                    balance.setDineroTotal(balanceOptional.get().getDineroTotal()+transaccion.getValor());
                }
            } else if (transaccion.getTipo().equals(TipoTransaccion.ENVIO)) {
                if(transaccion.getValor()>balanceOptional.get().getDineroDisponible()){
                    throw new ValidationException("Excede el valor disponible");
                }
                balance.setDineroInvertido(productoService.obtenerDineroInvertido());
                balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()-transaccion.getValor());
                balance.setDineroTotal(balanceOptional.get().getDineroTotal());
            }else if(transaccion.getTipo().equals(TipoTransaccion.GASTO)){
                if(transaccion.getValor()>balanceOptional.get().getDineroDisponible()){
                    throw new ValidationException("Excede el valor disponible");
                }
                balance.setDineroInvertido(productoService.obtenerDineroInvertido());
                balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()-transaccion.getValor());
                balance.setDineroTotal(balanceOptional.get().getDineroTotal()-transaccion.getValor());
            } else if (transaccion.getTipo().equals(TipoTransaccion.INGRESO)) {
                balance.setDineroInvertido(productoService.obtenerDineroInvertido());
                balance.setDineroDisponible(balanceOptional.get().getDineroDisponible()+transaccion.getValor());
                balance.setDineroTotal(balanceOptional.get().getDineroTotal()+transaccion.getValor());

            }
            balance.setFecha(LocalDate.now());
            balance.setTransaccion(transaccion);
            transaccion.setBalance(balance);
            balanceService.guardarBalance(balance);
        }
        else if(balanceOptional.isEmpty()){
            if(transaccion.getTipo()==TipoTransaccion.INGRESO){
                balance.setDineroDisponible(transaccion.getValor());
                balance.setDineroTotal(transaccion.getValor());
                balance.setFecha(LocalDate.now());
                balance.setTransaccion(transaccion);
                transaccion.setBalance(balance);
                balanceService.guardarBalance(balance);
            }
        }

    }
    public Transaccion crearTransaccion(Double valor, TipoTransaccion tipo){
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDate.now());
        transaccion.setTipo(tipo);
        transaccion.setValor(valor);
        return transaccion;
    }
    public List<DetallesTransaccion> obtenerTodasTransacciones(){
        return transaccionRepository.obtenerOrdenado().stream().map(t->new DetallesTransaccion(t)).toList();
    }
    public List<DetallesTransaccion> obtenerTransaccionTipo(TipoTransaccion tipo){
        return transaccionRepository.findByTipo(tipo).stream().map(t->new DetallesTransaccion(t)).toList();
    }
    public Double obtenerGastoMes(){
        LocalDate fechaInicio = LocalDate.now().withDayOfMonth(1);
        LocalDate fechaFin = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        return transaccionRepository.gastoMes(fechaInicio,fechaFin);
    }



}
