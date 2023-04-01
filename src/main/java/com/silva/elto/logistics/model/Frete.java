package com.silva.elto.logistics.model;

import com.silva.elto.logistics.dto.ViaCepDto;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class Frete {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private double frete;

    public Frete(ViaCepDto viaCepDto) {
        this.cep = viaCepDto.getCep();
        this.rua = viaCepDto.getLogradouro();
        this.complemento = viaCepDto.getComplemento();
        this.bairro = viaCepDto.getBairro();
        this.cidade = viaCepDto.getLocalidade();
        this.estado = viaCepDto.getUf();
        this.frete = 0.0;
    }

    private double calcularFrete() {
        return 0.0;
    }
}
