package com.lightit.automated.qa.test.api.conditions;

import io.restassured.response.Response;

public interface Condition {

    void check(Response response);

}

