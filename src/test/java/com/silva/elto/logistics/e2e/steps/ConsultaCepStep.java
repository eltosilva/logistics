package com.silva.elto.logistics.e2e.steps;

import com.silva.elto.logistics.dto.FreteDto;
import com.silva.elto.logistics.dto.ViaCepDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
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
import org.springframework.http.HttpStatus;

import java.net.URI;


public class ConsultaCepStep {

    private RequestSpecification requestSpecification;
    private Response response;


    @Given("Dado o CEP {string}")
    public void dado_um_cep_valido(String cep) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(URI.create("http://localhost:8080/v1/consulta-endereco"));
        builder.setContentType(ContentType.JSON);
        builder.setBody("{ \"cep\": \"" + cep + "\"}");

        requestSpecification = RestAssured.given().spec(builder.build());

    }

    @When("Quando consulta o CEP e calcula o valor do frete")
    public void quando_consulta_o_cep_e_calcula_o_valor_do_frete() {
        response = requestSpecification.when().post();
    }

    @Then("Entao verifica se o {string} e {double} do frete estão corretos")
    public void entao_verifica_se_o_valor_do_frete_está_correto(String uf, double valor) {
        Assertions.assertEquals(200, response.statusCode());

        FreteDto freteDto = response.as(FreteDto.class);
        Assertions.assertEquals(uf, freteDto.getEstado());
        Assertions.assertEquals(valor, freteDto.getFrete());
    }

    @Then("Entao retornara um bad request")
    public void verificaSeOCodigoDeStatusEhBadRequest(){
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    @Then("Entao retornara um not found")
    public void verificaSeOCodigoDeStatusEhNotFound(){
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.statusCode());
    }
}
