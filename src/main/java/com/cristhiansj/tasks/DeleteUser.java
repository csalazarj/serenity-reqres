package com.cristhiansj.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {

    private final int userId;

    public DeleteUser(int userId) {
        this.userId = userId;
    }

    public static Performable withId(int userId) {
        return instrumented(DeleteUser.class, userId);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Delete.from("/users/" + userId)
                        .with(requestSpecification ->
                                requestSpecification.contentType(ContentType.JSON))
        );
    }
}
