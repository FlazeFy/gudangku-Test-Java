package org.example.user;

import org.example.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {
    // Locator Button
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

    // Locator Text
    @FindBy(id = "total_item")
    private WebElement totalItemText;

    @FindBy(id = "total_fav")
    private WebElement totalFavText;

    @FindBy(id = "total_low")
    private WebElement totalLowText;

    @FindBy(id = "last_added")
    private WebElement lastAddedText;

    @FindBy(id = "most_category_context")
    private WebElement mostCategoryContextText;

    @FindBy(id = "most_category_total")
    private WebElement mostCategoryTotalText;

    @FindBy(id = "highest_price_name")
    private WebElement highestPriceNameText;

    @FindBy(id = "highest_price")
    private WebElement highestPriceText;

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    // Navigation Button
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

    // Text Value Int
    public boolean seeTotalItemValueNumber() {
        return isTextValidNumberValue(totalItemText);
    }

    public boolean seeTotalFavValueNumber() {
        return isTextValidNumberValue(totalFavText);
    }

    public boolean seeTotalLowValueNumber() {
        return isTextValidNumberValue(totalLowText);
    }

    public boolean seeMostCategoryTotalValueNumber() {
        return isTextValidNumberValue(mostCategoryTotalText);
    }

    public boolean seeHighestPriceValueNumber() {
        return isTextValidPriceValue(highestPriceText);
    }

    // Text Value String
    public boolean seeLastAddedString() {
        return isTextValidStringValue(lastAddedText);
    }

    public boolean seeMostCategoryContextString() {
        return isTextValidStringValue(mostCategoryContextText);
    }

    public boolean seeHighestPriceNameString() {
        return isTextValidStringValue(highestPriceNameText);
    }

    // Validate group of element
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

    public boolean isDashboardStatsHaveValidValue() {
        return seeTotalItemValueNumber() && seeTotalFavValueNumber() && seeTotalLowValueNumber()
                && seeMostCategoryTotalValueNumber() && seeHighestPriceValueNumber() && seeLastAddedString()
                && seeMostCategoryContextString() && seeHighestPriceNameString();
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
