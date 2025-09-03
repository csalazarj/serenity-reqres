package com.cristhiansj.runners;

import com.cristhiansj.abilities.InteractWithDb;
import com.cristhiansj.database.DatabaseConnectionInfo;
import com.cristhiansj.database.DatabaseType;
import com.cristhiansj.database.entity.Example;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SerenityRunner.class)
public class SerenityBddTest {

    private final String restApiUrl = "http://localhost:5000/api";

    @Test
    public void dataBaseConnectionTest(){

        DatabaseConnectionInfo connectionInfo = DatabaseConnectionInfo
                .builder()
                .username("root")
                .databaseType(DatabaseType.MYSQL)
//                .url("jdbc:mysql://localhost/test_automation")
                .url("jdbc:mysql://127.0.0.1:3306/test_automation?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC")
                .password("my-secret-pw")
                .entityNames(Stream.of(
                        Example.class
                )
                        .map(Class::getName)
                        .collect(Collectors.toList()))
                .build();

        Actor cristhian = Actor.named("Cristhian");
        cristhian.can(InteractWithDb.using(connectionInfo));

        EntityManager entityManager = InteractWithDb.as(cristhian).getManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Example> criteriaQuery = criteriaBuilder.createQuery(Example.class);

        Root<Example> userRoot = criteriaQuery.from(Example.class);

        Example queryResult = entityManager
                .createQuery(criteriaQuery.
                        select(userRoot))
                .getSingleResult();

        System.out.println(queryResult);
    }
}
