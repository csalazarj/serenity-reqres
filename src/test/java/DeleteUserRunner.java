import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/eliminar_usuario.feature",
        glue = "com.cristhiansj.stepdefinitions"
)
public class DeleteUserRunner {
}