package com.cristhiansj.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUser implements Task {

    private final String userId;
    private final Object userInfo;


    public UpdateUser(String userId, Object userInfo) {
        this.userId = userId;
        this.userInfo = userInfo;
    }

    public static Performable withInfo(String userId, Object userInfo) {
        return instrumented(UpdateUser.class, userId, userInfo);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/users/" + userId).with(
                        requestSpecification -> requestSpecification.
                                contentType(ContentType.JSON).
                                body(userInfo)
                )
        );
    }
}
