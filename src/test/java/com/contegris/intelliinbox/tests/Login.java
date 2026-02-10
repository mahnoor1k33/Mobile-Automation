package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.LoginPage;
import com.contegris.intelliinbox.utils.ConfigReader;
import org.testng.annotations.Test;

public class Login extends BaseTest { // ← Extends BaseTest, NOT BasePage

    @Test
    public void verifyLoginFlow() throws Exception {
        // driver is already initialized by @BeforeMethod in BaseTest

        LoginPage loginPage = new LoginPage(driver); // ← Pass driver to page

        loginPage.launchApp();
        loginPage.enterDomainIfVisible(ConfigReader.get("domain"));
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
       // loginPage.verifyMyInboxScreen();
    }
}