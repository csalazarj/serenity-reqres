package com.cristhiansj.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/consultar_lista_de_usuarios.feature",
        glue = {"com.cristhiansj"},
        plugin = {"pretty"}
)
public class GetUsersRunner {
}