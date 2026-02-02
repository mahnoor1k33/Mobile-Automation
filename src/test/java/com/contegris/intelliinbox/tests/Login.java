package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BasePage;
import com.contegris.intelliinbox.pages.LoginPage;
import com.contegris.intelliinbox.utils.ConfigReader;
import org.testng.annotations.Test;

public class Login extends BasePage {

    @Test
    public void verifyLoginFlow() throws Exception {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.launchApp();
        loginPage.enterDomainIfVisible(ConfigReader.get("domain"));
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }
}
