package com.bancolombia.cuentabancaria.controller;

import com.bancolombia.cuentabancaria.model.entity.CuentaBancariaEntity;
import com.bancolombia.cuentabancaria.model.request.CuentaBancariaRQ;
import com.bancolombia.cuentabancaria.model.request.TransaccionRQ;
import com.bancolombia.cuentabancaria.service.CuentaBancariaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/cuenta")
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaBancariaService;

    public CuentaBancariaController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @PostMapping(path = "/saldo")
    public ResponseEntity<Object> saldo(@Valid @RequestBody CuentaBancariaRQ cuentaBancariaRQ){
        Map<String, Object> message = new HashMap<>();
        message.put("saldo", cuentaBancariaService.getCuenta(cuentaBancariaRQ.getIdCuenta()).getSaldo());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/deposito")
    public ResponseEntity<Object> deposito(@Valid @RequestBody TransaccionRQ transaccionRQ){
        Map<String, Object> message = new HashMap<>();
        CuentaBancariaEntity cuentaEntity = cuentaBancariaService.deposito(transaccionRQ);
        message.put("message", "Deposito exitoso");
        message.put("saldo", cuentaEntity.getSaldo());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/retiro")
    public ResponseEntity<Object> retiro(@Validated @RequestBody TransaccionRQ transaccionRQ){
        Map<String, Object> message = new HashMap<>();
        CuentaBancariaEntity cuentaEntity = cuentaBancariaService.retiro(transaccionRQ);
        message.put("message", "Deposito exitoso");
        message.put("saldo", cuentaEntity.getSaldo());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
