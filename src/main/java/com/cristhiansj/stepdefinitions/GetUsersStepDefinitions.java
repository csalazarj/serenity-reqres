package com.cristhiansj.stepdefinitions;

import com.cristhiansj.models.users.Datum;
import com.cristhiansj.questions.GetUsersQuestion;
import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.GetUsers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetUsersStepDefinitions {

    private static final String restURLApi = "http://localhost:5000/api";
    Actor ivan;
    Datum user;


    @Given("Ivan requiere validar el funcionamiento de la API de consulta")
    public void ivan_requiere_validar_el_funcionamiento_de_la_api_de_consulta() {
        ivan = Actor.named("Ivan")
                .whoCan(CallAnApi.at(restURLApi));
    }
    @When("el envia la pagina de la lista a consultar")
    public void el_envia_la_pagina_de_la_lista_a_consultar() {
        ivan.attemptsTo(
                GetUsers.fromPage(1)
        );
    }
    @Then("el debe obtener los datos relacionados a los clientes de la pagina")
    public void el_debe_obtener_los_datos_relacionados_a_los_clientes_de_la_pagina() {
        ivan.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );

        user = new GetUsersQuestion().answeredBy(ivan).getData().stream().filter(x -> x.getId() == 1).findFirst().orElse(null);

        ivan.should(
                seeThat("usuario no es nulo", act -> user, notNullValue())
        );

        ivan.should(
                seeThat("el email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("el avatar del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
        );
    }
}
