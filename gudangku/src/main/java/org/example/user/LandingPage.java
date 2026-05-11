package org.example.user;

import org.example.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {
    // Locator
    @FindBy(id = "nav_chat_btn")
    private WebElement menuButtonChat;

    @FindBy(id = "nav_inventory_btn")
    private WebElement menuButtonInventory;

    @FindBy(id = "nav_stats_btn")
    private WebElement menuButtonStats;

    @FindBy(id = "nav_calendar_btn")
    private WebElement menuButtonCalendar;

    @FindBy(id = "nav_report_btn")
    private WebElement menuButtonReport;

    @FindBy(id = "nav_history_btn")
    private WebElement menuButtonHistory;

    @FindBy(id = "nav_profile_btn")
    private WebElement menuButtonProfile;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    // Navigation
    public void clickMenuButtonChat() {
        waitForElementToBeVisible(menuButtonChat);
        menuButtonChat.click();
    }

    public void clickMenuButtonInventory() {
        waitForElementToBeVisible(menuButtonInventory);
        menuButtonInventory.click();
    }

    public void clickMenuButtonStats() {
        waitForElementToBeVisible(menuButtonStats);
        menuButtonStats.click();
    }

    public void clickMenuButtonCalendar() {
        waitForElementToBeVisible(menuButtonCalendar);
        menuButtonCalendar.click();
    }

    public void clickMenuButtonReport() {
        waitForElementToBeVisible(menuButtonReport);
        menuButtonReport.click();
    }

    public void clickMenuButtonHistory() {
        waitForElementToBeVisible(menuButtonHistory);
        menuButtonHistory.click();
    }

    public void clickMenuButtonProfile() {
        waitForElementToBeVisible(menuButtonProfile);
        menuButtonProfile.click();
    }

    public boolean isAllMenuButtonsVisible() {
        return isElementVisible(menuButtonChat)
                && isElementVisible(menuButtonInventory)
                && isElementVisible(menuButtonStats)
                && isElementVisible(menuButtonCalendar)
                && isElementVisible(menuButtonReport)
                && isElementVisible(menuButtonHistory)
                && isElementVisible(menuButtonProfile);
    }

    public boolean isAllMenuButtonsHaveHref() {
        return isMenuButtonExistsAndHasHref(menuButtonChat, "/chat")
                && isMenuButtonExistsAndHasHref(menuButtonInventory, "/inventory")
                && isMenuButtonExistsAndHasHref(menuButtonStats, "/stats")
                && isMenuButtonExistsAndHasHref(menuButtonCalendar, "/calendar")
                && isMenuButtonExistsAndHasHref(menuButtonReport, "/report")
                && isMenuButtonExistsAndHasHref(menuButtonHistory, "/history")
                && isMenuButtonExistsAndHasHref(menuButtonProfile, "/profile");
    }

    private boolean isMenuButtonExistsAndHasHref(WebElement button, String expectedHref) {
        waitForElementToBeVisible(button);
        String onclickValue = button.getDomAttribute("onclick");
        return button.isDisplayed()
                && onclickValue != null
                && onclickValue.contains(expectedHref);
    }

    public boolean verifyRedirectToLanding() {
        waitForUrlContains("/");
        return driver.getCurrentUrl().contains("/");
    }
}
