package com.lightit.automated.qa.test.ui.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.lightit.automated.qa.test.api.models.UserInfo;
import com.lightit.automated.qa.test.api.models.userProfileModel.PostUserProfileModel;
import com.lightit.automated.qa.test.api.servise.createUserService.CreateUserService;
import com.lightit.automated.qa.test.api.steps.postUserProfile.PostUserProfileStep;
import com.lightit.automated.qa.test.ui.page.login.SignInPage;
import com.lightit.automated.qa.test.ui.page.mainPage.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class LoginPageTest  {

    CreateUserService createUserService = new CreateUserService();

    PostUserProfileStep postUserProfileStep = new PostUserProfileStep();

    UserInfo userInfo = postUserProfileStep.postUserProfile();


    @Test(description = "Test Login by user cred")
    public void testSignInByUserCred() {
        SignInPage
                .open()
                .loginAs(userInfo)
                .navigatesTo(MainPage.class)
                .userName.shouldHave(Condition.appear);

        MainPage.logout();
    }

    @Test(description = "Login Invalid Credentials")
    public void testLoginInvalidCredentials() {

        PostUserProfileModel postUserProfileModel = new PostUserProfileModel().randomize();

        createUserService
                .createUser(postUserProfileModel);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(postUserProfileModel.getEmail());
        userInfo.setName("asd");
        userInfo.setPass("asd");

        SignInPage
                .open()
                .loginAs(userInfo)
                .navigatesTo(SignInPage.class).shouldHaveInvalidCredError("Unable to log in with provided credentials.");
    }

    @AfterMethod
    public void afterMethod(){
        Selenide.clearBrowserCookies();
    }
}
