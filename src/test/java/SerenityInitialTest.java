import com.cristhiansj.facts.NetflixPlans;
import com.cristhiansj.models.users.Datum;
import com.cristhiansj.models.users.RegisterUserInfo;
import com.cristhiansj.questions.GetUsersQuestion;
import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.GetUsers;
import com.cristhiansj.tasks.RegisterUser;
import com.cristhiansj.tasks.UpdateUser;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.screenshots.ScreenshotDigest;
import org.hibernate.sql.Update;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTest {

    private static final String restURLApi = "http://localhost:5000/api";

    @Test
    public void getUsers() {

        Actor cristhian = Actor.named("Cristhian the QA")
                .whoCan(CallAnApi.at(restURLApi));

        cristhian.attemptsTo(
                GetUsers.fromPage(1)
        );

        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);

        cristhian.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );

        Datum user = new GetUsersQuestion().answeredBy(cristhian).getData().stream().filter(x -> x.getId() == 1).findFirst().orElse(null);

        cristhian.should(
                seeThat("usuario no es nulo", act -> user, notNullValue())
        );

        cristhian.should(
                seeThat("el email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("el avatar del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
        );


    }

    @Test
    public void registerUsersTest() {

        Actor cristhian = Actor.named("Cristhian the QA")
                .whoCan(CallAnApi.at(restURLApi));

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("Cristobal");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("tracey.ramos@reqres.in");
        registerUserInfo.setPassword("serenity");


//        String registerUserInfo = "{\n" +
//                "    \"name\": \"Cristobal Colon\",\n" +
//                "    \"job\": \"Navegante\",\n" +
//                "    \"email\": \"eve.holt@reqres.in\",\n" +
//                "    \"password\": \"pistol\"\n" +
//                "}";

        cristhian.attemptsTo(
                RegisterUser.withInfo(registerUserInfo)
        );
        cristhian.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );

    }


    @Test
    public void updateUsersTest() {

        Actor cristhian = Actor.named("Cristhian the QA")
                .whoCan(CallAnApi.at(restURLApi));

        RegisterUserInfo updaterUserInfo = new RegisterUserInfo();

        updaterUserInfo.setName("Cristobal");
        updaterUserInfo.setJob("Sailor");

        cristhian.attemptsTo(
                UpdateUser.withInfo("4", updaterUserInfo)
        );
        cristhian.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );
    }

    @Test
    public void factTest() {

        Actor Juan = Actor.named("Juan the QA")
                .whoCan(CallAnApi.at(restURLApi));

        Juan.has(NetflixPlans.toViewSeries());
    }
}