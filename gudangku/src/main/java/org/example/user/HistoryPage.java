package org.example.user;

import org.example.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoryPage extends BasePage {

    @FindBy(id = "history_holder")
    private WebElement historyHolder;

    @FindBy(css = "#history_holder .history-box")
    private List<WebElement> historyBoxes;

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isHistoryHolderVisible() {
        return isElementVisible(historyHolder);
    }

    public boolean isHistoryBoxExist() {
        return !historyBoxes.isEmpty();
    }

    public boolean isAllHistoryBoxesValid() {
        try {
            if (historyBoxes.isEmpty()) return false;

            for (WebElement historyBox : historyBoxes) {
                scrollToElement(historyBox);

                // Find child elements
                WebElement titleElement = historyBox.findElement(By.tagName("h6"));
                WebElement dateElement = historyBox.findElement(By.tagName("p"));
                WebElement deleteButton = historyBox.findElement(By.cssSelector("button.btn-delete"));

                // Validate h6 text (history_type and history_context)
                if (!isTextValidStringValue(titleElement)) return false;

                // Validate p text (created_at)
                if (!isTextValidStringValue(dateElement)) return false;

                // Validate delete button
                if (!deleteButton.isDisplayed()) return false;

                // Validate data-bs-target exists
                String modalTarget = deleteButton.getDomAttribute("data-bs-target");
                if (modalTarget == null || modalTarget.isBlank()) return false;

                // Validate target starts with #modalDelete_
                if (!modalTarget.startsWith("#modalDelete_")) return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyRedirectToHistory() {
        waitForUrlContains("/history");
        return driver.getCurrentUrl().contains("/history");
    }
}