package com.silva.elto.logistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroDto {
    @Schema(example = "Bad Request")
    private String mensagem;
    private String descricao;
}
