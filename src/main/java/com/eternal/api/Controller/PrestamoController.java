package com.eternal.api.Controller;

import com.eternal.api.Models.DTO.Register.RegistrarPrestamo;
import com.eternal.api.Models.DTO.Response.DetallesPrestamo;
import com.eternal.api.Models.DTO.Response.DetallesProducto;
import com.eternal.api.Models.DTO.Update.ActualizarPrestamo;
import com.eternal.api.Service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    @GetMapping("/todos")
    public ResponseEntity<List<DetallesPrestamo>> obtenerPrestamos() {
        List<DetallesPrestamo> detallesPrestamos = prestamoService.obtenerTodos();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(detallesPrestamos);
    }
    @PostMapping
    public ResponseEntity<?> registrarPrestamo(@RequestBody @Valid RegistrarPrestamo prestamo) {
        prestamoService.guardarPrestamo(prestamo);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(headers);
    }
    @GetMapping("/activos")
    public ResponseEntity<List<DetallesPrestamo>> obtenerPrestamosActivos() {
        List<DetallesPrestamo> detallesPrestamos = prestamoService.obtenerPrestamosActivos();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(detallesPrestamos);
    }
    @GetMapping("/inactivos")
    public ResponseEntity<List<DetallesPrestamo>> obtenerPrestamosInactivos() {
        List<DetallesPrestamo> detallesPrestamos = prestamoService.obtenerPrestamosInactivos()  ;
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(detallesPrestamos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesPrestamo> obtenerPrestamo(@PathVariable Long id){
        DetallesPrestamo prestamo = prestamoService.obtenerPrestamoId(id);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(prestamo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@RequestBody @Valid ActualizarPrestamo prestamo, @PathVariable Long id) {
        prestamoService.actualizarPrestamo(id,prestamo);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).build();
    }
}
