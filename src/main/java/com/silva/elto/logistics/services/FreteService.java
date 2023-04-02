package com.silva.elto.logistics.services;

import com.silva.elto.logistics.clients.CepClient;
import com.silva.elto.logistics.dto.CepDto;
import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;
import org.springframework.stereotype.Service;

@Service
public class FreteService {

    private CepClient cepClient;

    public FreteService(CepClient cepClient){
        this.cepClient = cepClient;
    }

    public FreteDto calcularFrete(CepDto cep){
        FreteDto freteDto = new FreteDto();
        cepClient.getCep(cep.getCep());

        return freteDto;
    }
}
