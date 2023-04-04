package com.silva.elto.logistics.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.silva.elto.logistics.dto.CepDto;
import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.services.FreteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class FreteServiceIntegrationTest {

    @Autowired
    private FreteService freteService;

    @Value("${url}")
    private String URL_BASE;

    @BeforeEach
    public void reset(){
        WireMock.reset();
    }

    @Test
    public void consultaComUmCepValido(){
        final CepDto CEP = new CepDto();
        CEP.setCep("68515000");
        final String RESPONSE_BODY = "{\n" +
                "  \"cep\": \"68515-000\",\n" +
                "  \"logradouro\": \"\",\n" +
                "  \"complemento\": \"\",\n" +
                "  \"bairro\": \"\",\n" +
                "  \"localidade\": \"Parauapebas\",\n" +
                "  \"uf\": \"PA\",\n" +
                "  \"ibge\": \"1505536\",\n" +
                "  \"gia\": \"\",\n" +
                "  \"ddd\": \"94\",\n" +
                "  \"siafi\": \"0595\"\n" +
                "}";

        WireMock.stubFor(WireMock.get(URL_BASE + "ws/" + CEP + "/json")
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(RESPONSE_BODY)
                ));
        FreteDto freteDto = freteService.calcularFrete(CEP);

        Assertions.assertEquals("Parauapebas", freteDto.getCidade());
        Assertions.assertEquals(20.83, freteDto.getFrete());
    }
}
