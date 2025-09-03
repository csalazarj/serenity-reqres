package com.cristhiansj.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/registro_de_usuarios.feature",
        glue = "com.cristhiansj.stepdefinitions",
        plugin = {"pretty"}
)
public class RegisterUserRunner {
}