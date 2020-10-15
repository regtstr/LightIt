package com.lightit.automated.qa.test.ui.page.login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import com.lightit.automated.qa.test.api.models.UserInfo;
import com.lightit.automated.qa.test.ui.Navigation;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.lightit.automated.qa.test.api.utilits.WaitHelper.sleep;

public class SignInPage {

    private SelenideElement name = $("[placeholder='Name']");
    private SelenideElement password = $("[placeholder='Password']");
    private SelenideElement email = $("[placeholder='Email']");
    private SelenideElement submitButton = $("[type='submit']");
    private SelenideElement errorField = $("body > qa-root > qa-sign-in > div > div > div:nth-child(2) > div > form > div.server-error.ng-star-inserted > qa-error-non > p");


    @Step("Open \"Login\" page")
    public static SignInPage open() {
        return Selenide.open("http://light-it-03.tk/#/auth/sign-in", SignInPage.class);
    }

    @Step("")
    public Navigation loginAs(UserInfo userInfo) {
        sleep(1500);
        name.click();
        name.setValue(userInfo.getName());
        password.setValue(userInfo.getPass());
        email.setValue(userInfo.getEmail());
        submitButton.click();
        sleep(500);
        return new Navigation();
    }

    public Navigation shouldHaveInvalidCredError(String exactMessage) {
        errorField.shouldHave(Condition.exactText(exactMessage));
        return new Navigation();
    }

}

