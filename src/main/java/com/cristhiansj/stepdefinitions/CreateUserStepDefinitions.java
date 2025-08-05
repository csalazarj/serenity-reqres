package com.cristhiansj.stepdefinitions;

import com.cristhiansj.models.users.CreateUserInfo;
import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.CreateUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserStepDefinitions {

    private static final String restURLApi = "http://localhost:5000/api";
    Actor carlos;
    CreateUserInfo createUserInfo;


    @Given("Carlos el administrador necesita agregar clientes al sistema")
    public void carlosElAdministradorNecesitaAgregarClientesAlSistema() {
        carlos = Actor.named("Carlos the admin")
                .whoCan(CallAnApi.at(restURLApi));
    }

    @When("el envia la informacion requerida para la creacion")
    public void elEnviaLaInformacionRequeridaParaLaCreacion() {
        createUserInfo = new CreateUserInfo();
        createUserInfo.setName("Margarita");
        createUserInfo.setJob("Analist");

        carlos.attemptsTo(
                CreateUser.withInfo(createUserInfo)
        );
    }

    @Then("el debe obtener la informacion de id y fecha actual")
    public void elDebeObtenerLaInformacionDeIdYFechaActual() {
        carlos.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(201))
        );
    }
}
