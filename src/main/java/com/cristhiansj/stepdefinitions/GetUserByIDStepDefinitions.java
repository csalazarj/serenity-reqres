package com.cristhiansj.stepdefinitions;

import com.cristhiansj.models.users.Datum;
import com.cristhiansj.questions.GetUserQuestion;
import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.GetSingleUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetUserByIDStepDefinitions {
    private static final String restURLApi = "http://localhost:5000/api";
    Actor paula;
    Datum user;

    @Given("^Paula la desarrolladora requiere validar el funcionamiento de la API de consulta$")
    public void paulaLaDesarrolladoraRequiereValidarElFuncionamientoDeLaAPIDeConsulta() {
        paula = Actor.named("Paula the developer")
                .whoCan(CallAnApi.at(restURLApi));
    }

    @When("^ella envia el ID del usuario a consultar$")
    public void ellaEnviaElIDDelUsuarioAConsultar() {
        paula.attemptsTo(
                GetSingleUser.withId(1)
        );

        user = new GetUserQuestion().answeredBy(paula).getData();
    }

    @Then("^ella debe obtener los datos relacionados al cliente$")
    public void ellaDebeObtenerLosDatosRelacionadosAlCliente() {
        paula.should(
                seeThat("usuario no es nulo", act -> user, notNullValue())
        );

        paula.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );

        paula.should(
                seeThat("el email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("el avatar del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
        );
    }


    @Given("Paula requiere validar el comportamiendo de la API al fallar")
    public void paula_requiere_validar_el_comportamiendo_de_la_api_al_fallar() {
        paula = Actor.named("Paula the developer")
                .whoCan(CallAnApi.at(restURLApi));
    }
    @When("ella envia el ID de un usuario que no existe en la base de datos")
    public void ella_envia_el_id_de_un_usuario_que_no_existe_en_la_base_de_datos() {
        paula.attemptsTo(
                GetSingleUser.withId(485)
        );
    }
    @Then("ella debe obtener el codigo de error relacionado")
    public void ella_debe_obtener_el_codigo_de_error_relacionado() {
        paula.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(404))
        );

    }

}
