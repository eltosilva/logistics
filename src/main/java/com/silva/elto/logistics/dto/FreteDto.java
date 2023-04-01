package com.silva.elto.logistics.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FreteDto {
    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Double frete;
}
