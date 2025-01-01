package com.eternal.api.Controller;

import com.eternal.api.Models.DTO.Response.DetallesBalance;
import com.eternal.api.Service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;


    @GetMapping
    public ResponseEntity<DetallesBalance> obtenerBalance(){
        DetallesBalance balance = balanceService.obtenerDetallesBalance();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(balance);
    }
}
