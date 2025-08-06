package com.cristhiansj.stepdefinitions;

import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.DeleteUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteUserStepDefinitions {

    private static final String restURLApi = "http://localhost:5000/api";
    Actor felipe;

    @Given("Felipe el adminstrador requiere eliminar un usuario")
    public void felipe_el_adminstrador_requiere_eliminar_un_usuario() {
        felipe = Actor.named("Felipe the admin")
                .whoCan(CallAnApi.at(restURLApi));
    }
    @When("el envia el ID del usuario a eliminar")
    public void el_envia_el_id_del_usuario_a_eliminar() {
        felipe.attemptsTo(
                DeleteUser.withId(1)
        );
    }
    @Then("el debe obtener un codido de respuesta acorde")
    public void el_debe_obtener_un_codido_de_respuesta_acorde() {
        felipe.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(204))
        );
    }

}
