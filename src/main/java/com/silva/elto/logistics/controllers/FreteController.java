package com.silva.elto.logistics.controllers;

import com.silva.elto.logistics.dto.CepDto;
import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;
import com.silva.elto.logistics.services.FreteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreteController {

    private FreteService freteService;

    public FreteController(FreteService freteService){
        this.freteService = freteService;
    }

    @PostMapping("/fretes")
    public ResponseEntity<FreteDto> hello(@RequestBody CepDto cepDto){
        FreteDto cep = freteService.calcularFrete(cepDto);
        return ResponseEntity.ok(cep);
    }
}
