package com.cristhiansj.tasks;

import com.cristhiansj.interactions.Get;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetSingleUser implements Task {

    private final int userId;

    public GetSingleUser(int userId) {
        this.userId = userId;
    }

    public static Performable withId(int userId) {
        return instrumented(GetSingleUser.class, userId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/users/" + userId)
                        .with(requestSpecification
                                -> requestSpecification.contentType(ContentType.JSON))
        );
    }
}
