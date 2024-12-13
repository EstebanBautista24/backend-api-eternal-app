package com.eternal.api.Controller;

import com.eternal.api.Models.DTO.Register.RegistrarTransaccion;
import com.eternal.api.Models.DTO.Register.Tipo;
import com.eternal.api.Models.DTO.Response.DetallesTransaccion;
import com.eternal.api.Models.DTO.Response.Gasto;
import com.eternal.api.Models.TipoTransaccion;
import com.eternal.api.Service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaccion")
@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @GetMapping("/todas")
    public ResponseEntity<List<DetallesTransaccion>> obtenerTodas(){
        List<DetallesTransaccion> transacciones = transaccionService.obtenerTodasTransacciones();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(transacciones);
    }

    @PostMapping("/tipo")
    public ResponseEntity<List<DetallesTransaccion>> obtenerPorTipo(@RequestBody @Valid Tipo tipo){
        System.out.println(tipo);
        TipoTransaccion tipoTransaccion = TipoTransaccion.fromString(tipo.getTipo());
        List<DetallesTransaccion> transacciones = transaccionService.obtenerTransaccionTipo(tipoTransaccion);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(transacciones);
    }

    @PostMapping
    public ResponseEntity<?> registrarTransaccion(@RequestBody @Valid RegistrarTransaccion registrarTransaccion){
        transaccionService.guardarTransaccion(registrarTransaccion);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(registrarTransaccion);
    }
    @GetMapping("/gasto")
    public ResponseEntity<?> obtenerGastosMes(){
        Gasto gasto = new Gasto(transaccionService.obtenerGastoMes());
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(gasto);
    }
}
