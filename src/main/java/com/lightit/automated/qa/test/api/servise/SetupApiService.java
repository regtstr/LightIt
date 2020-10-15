package com.lightit.automated.qa.test.api.servise;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SetupApiService {

    public RequestSpecification baseSetupHeaders(){
        return RestAssured.given()
                .baseUri("http://light-it-03.tk/api")
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }

    public RequestSpecification baseSetupAuth(String name, String password){
        return RestAssured.given()
                .baseUri("http://light-it-03.tk/api")
                .contentType(ContentType.JSON)
                .auth().preemptive().basic(name, password)
                .filters(new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }
}
