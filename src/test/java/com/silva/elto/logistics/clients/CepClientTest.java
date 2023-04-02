package com.silva.elto.logistics.clients;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.silva.elto.logistics.dto.ViaCepDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class CepClientTest {

    @Autowired
    private CepClient cepClient;

    @Test
    public void algumTeste(){

        final String responseBody = "{\n" +
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

        WireMock.stubFor(WireMock.get("${url}")
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(responseBody)
                ));

        ViaCepDto cep = cepClient.getCep("68515000");

        Assertions.assertEquals("PA", cep.getUf());
    }
}