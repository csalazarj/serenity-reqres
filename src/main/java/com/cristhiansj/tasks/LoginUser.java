package com.cristhiansj.tasks;

import com.cristhiansj.interactions.Post;
import com.cristhiansj.models.users.LoginCredentials;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.apache.commons.net.bsd.RLoginClient;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginUser implements Task {

    private final LoginCredentials userCredentials;

    public LoginUser(LoginCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public static Performable withCrededential(LoginCredentials userCredentials) {
        return instrumented(LoginUser.class, userCredentials);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/login")
                        .with(requestSpecification ->
                                requestSpecification
                                        .contentType(ContentType.JSON)
                                        .body(userCredentials))
        );
    }
}
