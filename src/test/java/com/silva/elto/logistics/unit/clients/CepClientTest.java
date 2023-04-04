package com.silva.elto.logistics.unit.clients;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.silva.elto.logistics.clients.CepClient;
import com.silva.elto.logistics.dto.ViaCepDto;
import feign.FeignException;
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
class CepClientTest {

    @Autowired
    private CepClient cepClient;

    @Value("${url}")
    private String URL_BASE;

    @BeforeEach
    public void reset(){
        WireMock.reset();
    }
    @Test
    public void consultaDeUmCepValido(){

        final String CEP = "68515000";
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

        ViaCepDto cep = cepClient.getCep(CEP);

        Assertions.assertEquals("PA", cep.getUf());

    }

    @Test
    public void consultaDeUmCepInvalido(){
        final String CEP = "68515a00";
        final String RESPONSE_BODY = "<!DOCTYPE HTML>\n" +
                "<html lang=\"pt-br\">\n" +
                "\n" +
                "<head>\n" +
                "  <title>ViaCEP 400</title>\n" +
                "  <meta charset=\"utf-8\" />\n" +
                "  <style type=\"text/css\">\n" +
                "      h1 {\n" +
                "          color: #555;\n" +
                "          text-align: center;\n" +
                "          font-size: 4em;\n" +
                "}\n" +
                "      h2, h3 {\n" +
                "          color: #666;\n" +
                "          text-align: center;\n" +
                "          font-size: 3em;\n" +
                "}\n" +
                "      h3 {\n" +
                "          font-size: 1.5em;\n" +
                "}\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <h1>Http 400</h1>\n" +
                "    <h3>Verifique a URL</h3>\n" +
                "    <h3>{Bad Request\n" +
                "}</h3>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";

        WireMock.get(URL_BASE + "ws/" + CEP + "/json").willReturn(
                WireMock.aResponse()
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withHeader("Contente-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(RESPONSE_BODY)
        );

        Assertions.assertThrows(FeignException.class, () -> cepClient.getCep(CEP));
    }

}