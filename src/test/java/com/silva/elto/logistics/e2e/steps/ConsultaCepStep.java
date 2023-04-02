package com.silva.elto.logistics.e2e.steps;

import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;


public class ConsultaCepStep {

    private RequestSpecification requestSpecification;
    private Response response;

    @Before
    public void init(){
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(URI.create("https://viacep.com.br/"));
        builder.setBasePath("ws/{cep}/json");
        builder.setContentType(ContentType.JSON);

        requestSpecification = RestAssured.given().spec(builder.build());
    }
    @Given("Dado o CEP {string}")
    public void dado_um_cep_valido(String cep) {
        requestSpecification.pathParam("cep", cep);
    }
    @When("Quando consulta o CEP e calcula o valor do frete")
    public void quando_consulta_o_cep_e_calcula_o_valor_do_frete() {
        response = requestSpecification.when().get();
    }
    @Then("Entao verifica se o {string} e {double} do frete estão corretos")
    public void entao_verifica_se_o_valor_do_frete_está_correto(String uf, double valor) {
        FreteDto freteDto = response.as(FreteDto.class);
        Assertions.assertEquals(uf, freteDto.getEstado());
        Assertions.assertEquals(valor, freteDto.getFrete());
    }
}
