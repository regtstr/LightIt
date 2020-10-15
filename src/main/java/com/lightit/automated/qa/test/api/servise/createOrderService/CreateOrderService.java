package com.lightit.automated.qa.test.api.servise.createOrderService;


import com.lightit.automated.qa.test.api.AssertableResponse;
import com.lightit.automated.qa.test.api.models.orderModel.postOrderModel.PostOrderModel;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileModel;
import com.lightit.automated.qa.test.api.servise.SetupApiService;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateOrderService extends SetupApiService {

    @Step("Send POST request")
    public AssertableResponse createOrder(String name, String password, PostOrderModel postOrderModel) {

        Response register =
                baseSetupAuth(name, password)
                        .body(postOrderModel)
                        .when()
                        .post("/orders/orders/")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

    @Step("Send POST request")
    public AssertableResponse createOrderNoAuth(PostOrderModel postOrderModel) {

        Response register =
                baseSetupHeaders()
                        .body(postOrderModel)
                        .when()
                        .post("/orders/orders/")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

}
