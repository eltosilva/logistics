package com.silva.elto.logistics.controllers;

import com.silva.elto.logistics.dto.CepDto;
import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.services.FreteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta-endereco")
public class FreteController {

    private FreteService freteService;

    public FreteController(FreteService freteService){
        this.freteService = freteService;
    }

    @Operation(
            description = "Calculo do frete para um CEP informado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Dados do frete",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FreteDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))
            )
    })
    @PostMapping
    public ResponseEntity<FreteDto> calcularFrete(@RequestBody @Valid CepDto cepDto){
        FreteDto cep = freteService.calcularFrete(cepDto);
        return ResponseEntity.ok(cep);
    }
}
