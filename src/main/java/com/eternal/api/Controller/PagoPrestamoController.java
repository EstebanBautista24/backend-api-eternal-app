package com.eternal.api.Controller;


import com.eternal.api.Models.DTO.Register.RegistrarPagoPrestamo;
import com.eternal.api.Service.PagoPrestamoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagoPrestamo")
public class PagoPrestamoController {
    @Autowired
    private PagoPrestamoService pagoPrestamoService;

    @PostMapping("/{id}")
    public ResponseEntity<?> registrarPagoPrestamo(@RequestBody @Valid RegistrarPagoPrestamo registrarPagoPrestamo,@PathVariable Long id) {
        pagoPrestamoService.guardarPagoPrestamo(id,registrarPagoPrestamo);
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(registrarPagoPrestamo);
    }
}
