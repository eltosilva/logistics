package com.silva.elto.logistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.IllegalFormatConversionException;

@Setter
@Getter
public class CepDto {

    @Schema(example = "68515000")
    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "CEP no formato inválido")
    private String cep;

}
