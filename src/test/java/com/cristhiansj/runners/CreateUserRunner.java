package com.cristhiansj.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/crear_un_usuario.feature",
        glue = {"com.cristhiansj"},
        plugin = {"pretty"}
)
public class CreateUserRunner {
}