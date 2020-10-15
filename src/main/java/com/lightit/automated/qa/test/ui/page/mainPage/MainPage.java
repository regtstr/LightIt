package com.lightit.automated.qa.test.ui.page.mainPage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.lightit.automated.qa.test.ui.page.login.SignInPage;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public static SelenideElement userName = $("[class='user-name']");
    public static SelenideElement logout = $("[class='link']");

    public static MainPage open(){
        return Selenide.open("http://light-it-03.tk/#/", MainPage.class);
    }

    public static SignInPage logout(){
        sleep(1500);
        userName.click();
        sleep(1500);
        logout.click();

        return new SignInPage();
    }

}
