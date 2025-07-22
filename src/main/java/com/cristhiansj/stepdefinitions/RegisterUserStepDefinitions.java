package com.cristhiansj.stepdefinitions;

import com.cristhiansj.models.users.RegisterUserInfo;
import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.RegisterUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.PendingException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterUserStepDefinitions {

    private static final String restURLApi = "http://localhost:5000/api";
    Actor cristhian;
    RegisterUserInfo registerUserInfo;


    @Given("^Julian es un cliente que quiere poder administrar sus productos bancarios$")
    public void julianEsUnClienteQueQuierePoderAdministrarSusProductosBancarios() {

        cristhian = Actor.named("Cristhian the QA").whoCan(CallAnApi.at(restURLApi));
    }

    @When("^el envia la informacion requerida para el registro$")
    public void elEnviaLaInformacionRequeridaParaElRegistro() {

        registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("Cristobal");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("tracey.ramos@reqres.in");
        registerUserInfo.setPassword("serenity");

        cristhian.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );
    }

    @Then("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void elDebeObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {

        cristhian.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );
    }
}
