package com.silva.elto.logistics.services;

import com.silva.elto.logistics.clients.CepClient;
import com.silva.elto.logistics.dto.CepDto;
import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;
import com.silva.elto.logistics.dto.mapper.ViaCepDtoToFreteDto;
import com.silva.elto.logistics.persistence.entities.Estado;
import com.silva.elto.logistics.persistence.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FreteService {

    private CepClient cepClient;
    private EstadoRepository estadoRepository;

    public FreteService(CepClient cepClient, EstadoRepository estadoRepository){
        this.cepClient = cepClient;
        this.estadoRepository = estadoRepository;
    }

    public FreteDto calcularFrete(CepDto cep){
        ViaCepDto viaCepDto = cepClient.getCep(cep.getCep());
        System.out.println("Calculando o frete");

        if(viaCepDto.isErro()){
            System.out.println("CEP não encontrado");
            throw new NoSuchElementException("CEP não encontrado.");
        }

        FreteDto freteDto = ViaCepDtoToFreteDto.mapper(viaCepDto);
        Optional<Estado> optionalSigla = estadoRepository.findBySigla(viaCepDto.getUf());
        Estado estado = optionalSigla.orElseThrow();

        freteDto.setFrete(estado.getRegiao().getValor().doubleValue());

        return freteDto;
    }
}
