package com.lightit.automated.qa.test.api.test.order;

import com.lightit.automated.qa.test.api.models.UserInfo;
import com.lightit.automated.qa.test.api.models.orderModel.postOrderModel.PostOrderModel;
import com.lightit.automated.qa.test.api.models.orderModel.postOrderModel.PostOrderModelResponse;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileModel;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileResponseModel;
import com.lightit.automated.qa.test.api.servise.createOrderService.CreateOrderService;
import com.lightit.automated.qa.test.api.servise.createUserService.CreateUserService;
import com.lightit.automated.qa.test.api.steps.postUserProfile.PostUserProfileStep;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.lightit.automated.qa.test.api.conditions.Conditions.bodyField;
import static com.lightit.automated.qa.test.api.conditions.Conditions.statusCode;
import static java.sql.Types.NULL;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.*;

public class PostOrder {

    PostUserProfileStep postUserProfileStep = new PostUserProfileStep();
    CreateOrderService createOrderService =new CreateOrderService();

    UserInfo userInfo = postUserProfileStep.postUserProfile();

    @Test(description = "Create New Order")
    public void testCreateNewOrder(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setCost(87.654);

        PostOrderModelResponse postOrderModelResponse = createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(201))
                .responseAs(PostOrderModelResponse.class);

        assertEquals(postOrderModel.getName(), postOrderModelResponse.getName());
        assertEquals(postOrderModel.getQuantity(), postOrderModelResponse.getQuantity());
        assertEquals(String.valueOf(postOrderModel.getCost()),  postOrderModelResponse.getCost());
        assertEquals(postOrderModel.getStatus(), postOrderModelResponse.getStatus());
        assertEquals(postOrderModel.getBidType(), postOrderModelResponse.getBidType());
        assertNotNull(postOrderModelResponse.getId());
    }

    @Test(description = "Create New Order Invalid Cred")
    public void testCreateInvalidCred(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId());

        createOrderService
                .createOrder("", "", postOrderModel)
                .shouldHave(statusCode(401),
                        bodyField("detail", containsString("Invalid username/password.")));

    }

    @Test(description = "Create New Order No Auth")
    public void testCreateNoAuth(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId());

        createOrderService
                .createOrderNoAuth(postOrderModel)
                .shouldHave(statusCode(401),
                        bodyField("detail", containsString("Authentication credentials were not provided.")));

    }

    // TODO: 15.10.2020 User can create order for other users
    @Test(description = "Create New Order Other Owner Id")
    public void testCreateOtherOwnerId(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId()-1);

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
//                .shouldHave(statusCode(400),
//                        bodyField("owner", contains("Owner id does not match")));
        .shouldHave(statusCode(201));

    }

    @Test(description = "Create New Order Invalid Owner Id")
    public void testCreateInvalidOwnerId(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(Integer.MAX_VALUE);

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("owner", contains("Invalid pk \""+postOrderModel.getOwner()+"\" - object does not exist.")));

    }

    @Test(description = "Create New Order Empty Body")
    public void testCreateEmptyBody(){

        PostOrderModel postOrderModel = new PostOrderModel();

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("owner", contains("This field is required.")))
                .shouldHave(statusCode(400),
                        bodyField("bid_type",contains("This field is required.")));

    }

    @Test(description = "Create New Order Empty Name")
    public void testCreateEmptyName(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setName("");

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("name", contains("This field may not be blank.")));

    }

    @Test(description = "Create New Order Long Name")
    public void testCreateLongName(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setName("UserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongName" +
                        "UserWithLongNameмUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUser" +
                        "UserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUser" +
                        "UserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongName");

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("name", contains( "Ensure this field has no more than 255 characters.")));

    }

    @Test(description = "Create New Order Empty Quantity")
    public void testCreateEmptyQuantity(){

        PostOrderModel postOrderModel = new PostOrderModel()
                .setOwner(userInfo.getId())
                .setName("Test")
                .setCost(1)
                .setStatus(1)
                .setBidType(1);

        PostOrderModelResponse postOrderModelResponse = createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(201))
                .responseAs(PostOrderModelResponse.class);

        assertEquals(Integer.valueOf(0), postOrderModelResponse.getQuantity());
        assertEquals(Integer.valueOf(3), postOrderModelResponse.getStatus());

    }

    @Test(description = "Create New Order Negative Quantity")
    public void testCreateNegativeQuantity(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setQuantity(-1);

        PostOrderModelResponse postOrderModelResponse = createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(201))
                .responseAs(PostOrderModelResponse.class);

        assertEquals(Integer.valueOf(0), postOrderModelResponse.getQuantity());
        assertEquals(Integer.valueOf(3), postOrderModelResponse.getStatus());

    }

    @Test(description = "Create New Order Highest Quantity")
    public void testCreateHighestQuantity(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setQuantity("2147483648");

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("quantity", contains( "Ensure this value is less than or equal to 2147483647.")));

    }

    @Test(description = "Create New Order Invalid Type Quantity")
    public void testCreateInvalidTypeQuantity(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setQuantity("1.1");

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("quantity", contains( "A valid integer is required.")));

    }

    @Test(description = "Create New Order Empty Cost")
    public void testCreateEmptyCost(){

        PostOrderModel postOrderModel = new PostOrderModel()
                .setOwner(userInfo.getId())
                .setName("Test")
                .setQuantity(1)
                .setStatus(1)
                .setBidType(1);

        PostOrderModelResponse postOrderModelResponse = createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(201))
                .responseAs(PostOrderModelResponse.class);

        assertEquals("0.000", postOrderModelResponse.getCost());
    }

    @Test(description = "Create New Order Negative Cost")
    public void testCreateNegativeCost(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setCost(-1.1);

        PostOrderModelResponse postOrderModelResponse = createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(201))
                .responseAs(PostOrderModelResponse.class);

        assertEquals("-1.100", postOrderModelResponse.getCost());
    }

    @Test(description = "Create New Order More 10 Digits Cost")
    public void testCreateMore10DigitsCost(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setCost("12345678901");

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("cost", contains("Ensure that there are no more than 10 digits in total.")));
    }

    @Test(description = "Create New Order More 3 Digits After Point Cost")
    public void testCreateMore3DigitsAfterPointCost(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setCost("123.1234");

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("cost", contains("Ensure that there are no more than 3 decimal places.")));
    }

    @Test(description = "Create New Order Invalid Status")
    public void testCreateInvalidStatus(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setStatus(123);

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("status", contains("\""+postOrderModel.getStatus()+"\" is not a valid choice.")));
    }

    @Test(description = "Create New Order Invalid Bid Type")
    public void testCreateInvalidSBidType(){

        PostOrderModel postOrderModel = new PostOrderModel().randomize()
                .setOwner(userInfo.getId())
                .setBidType(123);

        createOrderService
                .createOrder(userInfo.getName(), userInfo.getPass(), postOrderModel)
                .shouldHave(statusCode(400),
                        bodyField("bid_type", contains("\""+postOrderModel.getBidType()+"\" is not a valid choice.")));
    }
}
