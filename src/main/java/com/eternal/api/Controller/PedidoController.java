package com.eternal.api.Controller;


import com.eternal.api.Models.DTO.Register.RegistrarPedido;
import com.eternal.api.Service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> registrarPedido(@RequestBody @Valid RegistrarPedido registrarPedido){
        pedidoService.guardarPedido(registrarPedido);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

}
