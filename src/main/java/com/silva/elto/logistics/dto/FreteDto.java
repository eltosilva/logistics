package com.silva.elto.logistics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreteDto {
    @Schema(example = "68515-000")
    private String cep;
    @Schema(example = "rua Carajás, 40")
    private String rua;
    @Schema(example = "Qd. 03")
    private String complemento;
    @Schema(example = "Residêncial Bambuí")
    private String bairro;
    @Schema(example = "Parauapebas")
    private String cidade;
    @Schema(example = "PA")
    private String estado;
    @Schema(example = "20.83", type = "double")
    private Double frete;
}
