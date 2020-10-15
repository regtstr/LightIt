package com.lightit.automated.qa.test.ui.driverCapabilities;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider implements WebDriverProvider {

    private String remoteDriverHost = System.getProperty("selenoid.remote.driver.host");
    private String remoteDriverPort = System.getProperty("selenoid.remote.driver.post");


    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("78.0");
        browser.setCapability("enableVNC", true);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create(remoteDriverHost + ":" + remoteDriverPort + "/wd/hub").toURL(),
                    browser
            );
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
