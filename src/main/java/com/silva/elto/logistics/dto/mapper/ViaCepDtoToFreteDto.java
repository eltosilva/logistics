package com.silva.elto.logistics.dto.mapper;

import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;

public class ViaCepDtoToFreteDto {
    public static FreteDto mapper(ViaCepDto cepDto){
        FreteDto freteDto = new FreteDto();
        freteDto.setBairro(cepDto.getBairro());
        freteDto.setCep(cepDto.getCep());
        freteDto.setRua(cepDto.getLogradouro());
        freteDto.setEstado(cepDto.getUf());
        freteDto.setCidade(cepDto.getLocalidade());
        freteDto.setComplemento(cepDto.getComplemento());

        return freteDto;
    }
}
