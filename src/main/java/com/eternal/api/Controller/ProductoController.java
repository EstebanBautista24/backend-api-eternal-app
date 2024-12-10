package com.eternal.api.Controller;

import com.eternal.api.Models.DTO.Response.DetallesProducto;
import com.eternal.api.Models.DTO.Update.ActualizarProducto;
import com.eternal.api.Service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarProducto(@RequestBody ActualizarProducto actualizarProducto,@PathVariable Long id){
        productoService.actualizarProducto(id,actualizarProducto);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).build();
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<DetallesProducto>> obtenerDiposnibles(){
        List<DetallesProducto> productos= productoService.obtenerDisponibles();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(productos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetallesProducto> obtenerProducto(@PathVariable Long id){
        DetallesProducto detallesProducto = productoService.obtenerProductoId(id);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(detallesProducto);
    }
    @GetMapping("/vendidos")
    public ResponseEntity<List<DetallesProducto>> obtenerVendidos(){
        List<DetallesProducto> productos= productoService.obtenerVendidos();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(productos);
    }
    @GetMapping("/todos")
    public ResponseEntity<List<DetallesProducto>> obtenerTodos(){
        List<DetallesProducto> productos= productoService.obtenerTodos();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(productos);
    }
}
