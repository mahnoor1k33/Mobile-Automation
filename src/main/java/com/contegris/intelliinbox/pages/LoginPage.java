package com.contegris.intelliinbox.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginPage {
    private AndroidDriver driver;

    // ✅ Locators (same as your code)
    private final By intelliconApp = AppiumBy.accessibilityId("Predicted app: Intellicon CX9i");
    private final By nextButton = AppiumBy.accessibilityId("Next");
    private final By domainField = By.xpath("//android.widget.EditText");
    private final By usernameField = AppiumBy.xpath("(//android.widget.ImageView[@clickable='true' and @focusable='true'])[2]");
    private final By passwordField = AppiumBy.xpath("(//android.widget.ImageView[@clickable='true' and @focusable='true'])[3]");
    private final By signInButton = AppiumBy.xpath("//android.widget.Button[@content-desc=\"Sign In\"]");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void launchApp() {
        driver.findElement(intelliconApp).click();
        System.out.println("✅ Intellicon App launched successfully!");
    }

    public void enterDomainIfVisible(String domain) throws InterruptedException {
        List<WebElement> nextButtons = driver.findElements(nextButton);
        if (!nextButtons.isEmpty()) {
            System.out.println("✅ Domain screen detected");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement domainBox = wait.until(ExpectedConditions.visibilityOfElementLocated(domainField));

            domainBox.click();
            domainBox.clear();
            domainBox.sendKeys(domain);

            nextButtons.get(0).click();
            System.out.println("Domain entered and Next button clicked!");
            Thread.sleep(1000);
        } else {
            System.out.println("✅ Login screen detected directly");
        }
    }

    public void login(String username, String password) {
        WebElement usernameFieldElement = driver.findElement(usernameField);
        usernameFieldElement.click();
        driver.executeScript("mobile: type", Map.of("text", username));

        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.click();
        driver.executeScript("mobile: type", Map.of("text", password));

        driver.findElement(signInButton).click();
        System.out.println("✅ Logged in successfully!");
    }
}
