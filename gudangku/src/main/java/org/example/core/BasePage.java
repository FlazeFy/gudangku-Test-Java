package org.example.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementVisible(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void waitForUrlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    private String getVisibleElementText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText().trim();
    }

    public boolean isTextValidStringValue(WebElement element) {
        try {
            // Make sure its visible and get text
            String textValue = getVisibleElementText(element);
            if (textValue.isEmpty()) return false;

            return textValue.equals("-") || !textValue.isBlank();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTextValidNumberValue(WebElement element) {
        try {
            // Make sure its visible and get text
            String textValue = getVisibleElementText(element);
            if (textValue.isEmpty()) return false;

            // Process as valid number
            int value = Integer.parseInt(textValue);

            return value >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTextValidPriceValue(WebElement element) {
        try {
            // Make sure its visible and get text
            String textValue = getVisibleElementText(element);
            if (textValue.isEmpty()) return false;

            // Must start with "Rp. " and get the price nominal only
            if (!textValue.startsWith("Rp. ")) return false;
            String pricePart = textValue.replace("Rp. ", "").trim();

            // Validate price format using comma with split each thousand and get the price nominal without comma
            if (!pricePart.matches("^\\d{1,3}(,\\d{3})*$")) return false;
            String numericValue = pricePart.replace(",", "");

            // Process as valid number
            long value = Long.parseLong(numericValue);

            return value >= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
