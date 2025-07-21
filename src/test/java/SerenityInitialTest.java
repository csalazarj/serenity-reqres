import com.cristhiansj.facts.NetflixPlans;
import com.cristhiansj.models.users.CreateUserInfo;
import com.cristhiansj.models.users.Datum;
import com.cristhiansj.models.users.LoginCredentials;
import com.cristhiansj.models.users.RegisterUserInfo;
import com.cristhiansj.questions.GetUserQuestion;
import com.cristhiansj.questions.GetUsersQuestion;
import com.cristhiansj.questions.ResponseCode;
import com.cristhiansj.tasks.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
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
    public void getSingleUserTest() {
        Actor paula = Actor.named("Paula the developer")
                .whoCan(CallAnApi.at(restURLApi));

        paula.attemptsTo(
                GetSingleUser.withId(1)
        );
        paula.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200))
        );

        Datum user = new GetUserQuestion().answeredBy(paula).getData();
        paula.should(
                seeThat("usuario no es nulo", act -> user, notNullValue())
        );

        paula.should(
                seeThat("el email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("el avatar del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
        );
    }

    @Test
    public void getUsersTest() {

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
    public void getSingleUserNotFoundTest() {
        Actor paula = Actor.named("Paula the developer")
                .whoCan(CallAnApi.at(restURLApi));

        paula.attemptsTo(
                GetSingleUser.withId(485)
        );
        paula.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(404))
        );

    }

    @Test
    public void createUserTest() {
        Actor julian = Actor.named("Julian the QA")
                .whoCan(CallAnApi.at(restURLApi));


        CreateUserInfo createUserInfo = new CreateUserInfo();

        createUserInfo.setName("Margarita");
        createUserInfo.setJob("Analist");

        julian.attemptsTo(
                CreateUser.withInfo(createUserInfo)
        );
    }

    @Test
    public void deleteUserTest() {

        Actor erika = Actor.named("Erika Infra")
                .whoCan(CallAnApi.at(restURLApi));

        erika.attemptsTo(
                DeleteUser.withId(1)
        );

        erika.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(204))
        );

    }

    @Test
    public void loginSuccessfulTest() {
        Actor ivan = Actor.named("Ivan the implementator")
                .whoCan(CallAnApi.at(restURLApi));

        LoginCredentials loginUserInfo = new LoginCredentials();

        loginUserInfo.setEmail("eve.holt@reqres.in");
        loginUserInfo.setPassword("cityslicka");

        ivan.attemptsTo(
                LoginUser.withCrededential(loginUserInfo)
        );
        ivan.should(
                seeThat("el código de respuesta", ResponseCode.was(), equalTo(200)),
                seeThat("el body response trae el token", actor -> SerenityRest.lastResponse().jsonPath().getString("token"), notNullValue())
        );
    }


    @Test
    public void factTest() {

        Actor Juan = Actor.named("Juan the QA")
                .whoCan(CallAnApi.at(restURLApi));

        Juan.has(NetflixPlans.toViewSeries());
    }
}