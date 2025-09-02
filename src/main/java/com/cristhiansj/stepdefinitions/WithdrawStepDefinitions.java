package com.cristhiansj.stepdefinitions;

import com.cristhiansj.models.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WithdrawStepDefinitions {
    @Given("^Cristhian es un cliente con una cuenta de ahorros$")
    public void cristhian_es_un_cliente_con_una_cuenta_de_ahorros() {

    }
    @Given("^en su cuenta tiene un saldo disponible de (\\d+\\.\\d+)$")
    public void en_su_cuenta_tiene_un_saldo_disponible_de(Integer pesos) {
        Account myAccount = new Account();
        myAccount.deposit(pesos);

    }
    @When("el intenta retirar de su cuenta {int}")
    public void el_intenta_retirar_de_su_cuenta(Integer int1) {

    }
    @Then("el deberia obtener {int}")
    public void el_deberia_obtener(Integer int1) {

    }
    @Then("el nuevo saldo de su cuenta deberia ser {int}")
    public void el_nuevo_saldo_de_su_cuenta_deberia_ser(Integer int1) {

    }
}
