package com.silva.elto.logistics.unit.services;

import com.silva.elto.logistics.clients.CepClient;
import com.silva.elto.logistics.dto.CepDto;
import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;
import com.silva.elto.logistics.dto.mapper.ViaCepDtoToFreteDto;
import com.silva.elto.logistics.persistence.entities.Estado;
import com.silva.elto.logistics.persistence.entities.Regiao;
import com.silva.elto.logistics.persistence.repositories.EstadoRepository;
import com.silva.elto.logistics.services.FreteService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class FreteServiceUnitTest {

    @Mock
    private CepClient cepClient;

    @Mock
    private EstadoRepository estadoRepository;

    @InjectMocks
    private FreteService freteService;

    @BeforeEach
    public void reset(){
        Mockito.reset(cepClient);
    }
    @Test
    public void consultaComCepValido(){
        ViaCepDto viaCepDto = new ViaCepDto("68515-000", "", "", "", "Parauapebas", "PA","1505536", "", "94", "0595", false);
        FreteDto freteDtoExpected = ViaCepDtoToFreteDto.mapper(viaCepDto);
        freteDtoExpected.setFrete(20.83);

        Estado estado = new Estado();
        Regiao regiao = new Regiao();
        regiao.setValor(BigDecimal.valueOf(20.83));
        estado.setRegiao(regiao);

        Mockito.when(cepClient.getCep("68515000"))
                .thenReturn(viaCepDto);
        Mockito.when(estadoRepository.findBySigla("PA"))
                .thenReturn(Optional.of(estado));

        CepDto cepDto = new CepDto();
        cepDto.setCep("68515000");
        FreteDto freteDto = freteService.calcularFrete(cepDto);

        Assertions.assertEquals(freteDtoExpected, freteDto);
    }
}
