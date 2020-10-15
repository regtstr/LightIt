package com.lightit.automated.qa.test.api.test.userProfile;

import com.lightit.automated.qa.test.api.models.UserInfo;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileModel;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileResponseModel;
import com.lightit.automated.qa.test.api.servise.createUserService.CreateUserService;
import com.lightit.automated.qa.test.api.steps.postUserProfile.PostUserProfileStep;
import org.testng.annotations.Test;

import static com.lightit.automated.qa.test.api.conditions.Conditions.bodyField;
import static com.lightit.automated.qa.test.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.contains;
import static org.testng.Assert.assertEquals;

public class PostUserProfile {

    CreateUserService createUserService = new CreateUserService();
    PostUserProfileStep postUserProfileStep = new PostUserProfileStep();

    @Test(description = "Create new user")
    public void testCreateNewUser(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize();

        PostUserProfileResponseModel postUserProfileResponseModel = createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(201))
                .responseAs(PostUserProfileResponseModel.class);

        assertEquals(postUserProfileModel.getUsername(), postUserProfileResponseModel.getUser().getUsername());
        assertEquals(postUserProfileModel.getEmail(), postUserProfileResponseModel.getUser().getEmail());
    }

    @Test(description = "Create New User With Empty Name")
    public void testCreateNewUserWithEmptyName(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setUsername("");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("username", contains("This field may not be blank.")));
    }

    @Test(description = "Create New User With Spaces Name")
    public void testCreateNewUserWithSpacesName(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setUsername("      ");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("username", contains("This field may not be blank.")));
    }

    @Test(description = "Create New User With Long Name")
    public void testCreateNewUserWithLongName(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setUsername("UserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongName" +
                        "UserWithLongNameмUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUserWithLongNameUser");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("username", contains("Ensure this field has no more than 150 characters.")));
    }

    @Test(description = "Create New User With Special Characters Name")
    public void testCreateNewUserWithSpecialCharactersName(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setUsername("!@#$%^&*()");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("username", contains("Enter a valid username. This value may contain only letters, numbers, and @/./+/-/_ characters.")));
    }

    @Test(description = "Create New User With Existing Name")
    public void testCreateNewUserWithExistingName(){

        UserInfo userInfo = postUserProfileStep.postUserProfile();

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setUsername(userInfo.getName());

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("username", contains("A user with that username already exists.")));
    }

    @Test(description = "Create New User With Empty Email")
    public void testCreateNewUserWithEmptyEmail(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setEmail("");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("email", contains("This field may not be blank.")));
    }

    @Test(description = "Create New User With Spaces Email")
    public void testCreateNewUserWithSpacesEmail(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setEmail("      ");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("email", contains("This field may not be blank.")));
    }

    @Test(description = "Create New User With Invalid Email")
    public void testCreateNewUserWithInvalidEmail(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setEmail("Email");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("email", contains("Enter a valid email address.")));
    }

    @Test(description = "Create New User With Existing Email")
    public void testCreateNewUserWithExistingEmail(){

        UserInfo userInfo = postUserProfileStep.postUserProfile();

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setEmail(userInfo.getEmail());

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("email", contains("A user is already registered with this e-mail address.")));
    }

    @Test(description = "Create New User With Empty Password")
    public void testCreateNewUserWithEmptyPassword(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setPassword1("");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("password1", contains("This field may not be blank.")));
    }

    @Test(description = "Create New User With Short Password")
    public void testCreateNewUserWithShortPassword(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setPassword1("123a");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("password1", contains("This password is too short. It must contain at least 8 characters.")));
    }

    @Test(description = "Create New User With Only Numeric Password")
    public void testCreateNewUserWithOnlyNumericPassword(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setPassword1("192837465")
                .setPassword2("192837465");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("password1", contains("This password is entirely numeric.")));
    }

    @Test(description = "Create New User With Common Password")
    public void testCreateNewUserWithOnlyCommonPassword(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setPassword1("qwertyui")
                .setPassword2("qwertyui");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("password1", contains("This password is too common.")));
    }

    @Test(description = "Create New User With Different Password")
    public void testCreateNewUserWithOnlyDifferentPassword(){

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize()
                .setPassword1("qwe123qwe123")
                .setPassword2("12345678");

        createUserService
                .createUser(postUserProfileModel)
                .shouldHave(statusCode(400),
                        bodyField("non_field_errors", contains("The two password fields didn't match.")));
    }

}
