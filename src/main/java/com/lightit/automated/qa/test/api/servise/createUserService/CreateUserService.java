package com.lightit.automated.qa.test.api.servise.createUserService;


import com.lightit.automated.qa.test.api.AssertableResponse;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileModel;
import com.lightit.automated.qa.test.api.servise.SetupApiService;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateUserService extends SetupApiService {

    @Step("Send POST request")
    public AssertableResponse createUser(PostUserProfileModel postUserProfileModel) {

        Response register =
                baseSetupHeaders()
                        .body(postUserProfileModel)
                        .when()
                        .post("/profiles/")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

}
