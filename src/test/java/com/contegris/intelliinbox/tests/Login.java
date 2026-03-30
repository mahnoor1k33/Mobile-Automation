package com.contegris.intelliinbox.tests;

import com.contegris.intelliinbox.base.BaseTest;
import com.contegris.intelliinbox.pages.LoginPage;
import com.contegris.intelliinbox.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends BaseTest { // ← Extends BaseTest, NOT BasePage

    LoginPage loginPage;

    @BeforeMethod
    public void setUpPages() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.launchApp();
        System.out.println("Intellicon Cx9i App Launched Successfully");
    }

    @Test
    public void testLoginFlow() throws InterruptedException {

        // ===== Invalid Domain =====
        loginPage.enterDomain(ConfigReader.get("invalid_domain"));
        loginPage.verifyInvalidDomainToast();

        // ===== Valid Domain =====
        loginPage.clearDomainField();
        loginPage.enterDomain(ConfigReader.get("domain"));

        // ===== Invalid Credentials =====
        loginPage.enterCredentials(ConfigReader.get("invalid_username"), ConfigReader.get("password"));
        loginPage.clickSignIn();
        loginPage.verifyInvalidCredentialsToast();

        // ===== Valid Credentials =====
        loginPage.clearCredentials();
        loginPage.enterCredentials(ConfigReader.get("username"), ConfigReader.get("password"));
        loginPage.clickSignIn();

        // ===== Verify Home Screen =====
        loginPage.verifyMyInboxScreen();
    }

    @Test (enabled=false)
    public void verifyLoginFlow() throws Exception {

        // ===== Full Happy Path =====
        loginPage.login(ConfigReader.get("domain"), ConfigReader.get("username"), ConfigReader.get("password"));
        loginPage.verifyMyInboxScreen();
    }

}