package com.eternal.api.Controller;

import com.eternal.api.Models.DTO.Register.RegistrarVenta;
import com.eternal.api.Models.DTO.Response.Ganancia;
import com.eternal.api.Service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*",allowedHeaders = "*",exposedHeaders = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS})
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping("/{id}")
    public ResponseEntity<?> registrarVenta(@RequestBody @Valid RegistrarVenta registrarVenta,@PathVariable Long id) {
        ventaService.guardarVenta(id,registrarVenta);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }
    @GetMapping("/gananciaMes")
    public ResponseEntity<Ganancia> getGananciaMes(){
        Ganancia ganancia = new Ganancia(ventaService.obtenerGananciaMes());
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(ganancia);
    }
}
