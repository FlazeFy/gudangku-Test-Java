package user;

import core.BaseTest;
import core.DriverManager;
import core.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.user.HistoryPage;
import org.example.user.LandingPage;
import org.example.user.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HistoryPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(HistoryPageTest.class);

    @Test(priority = 2, groups = {"e2e"}, description = "User can see all history", retryAnalyzer = core.RetryAnalyzer.class)
    public void testUserCanSeeAllHistory() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        LandingPage landingPage = new LandingPage(DriverManager.getDriver());
        HistoryPage historyPage = new HistoryPage(DriverManager.getDriver());

        logger.info("Step 1: User login with valid credentials");
        Object[][] data = TestUtils.getTestData(
                System.getProperty("user.dir") + "/src/test/resources/data/login-data-test.xlsx",
                "login-tests"
        );
        String username = data[0][0].toString();
        String password = data[0][1].toString();

        loginPage.login(username, password);
        Assert.assertTrue(
                landingPage.verifyRedirectToLanding(),
                "User should be redirected to landing page after login"
        );

        logger.info("Step 2: Navigate to history page");
        landingPage.clickMenuButtonHistory();
        Assert.assertTrue(
                historyPage.verifyRedirectToHistory(),
                "User should be redirected to history page"
        );

        logger.info("Step 3: Verify history holder is visible");
        Assert.assertTrue(
                historyPage.isHistoryHolderVisible(),
                "History holder should be visible"
        );

        logger.info("Step 4: Verify history items exist and have valid structure");
        Assert.assertTrue(
                historyPage.isHistoryBoxExist(),
                "History items should exist"
        );
        Assert.assertTrue(
                historyPage.isAllHistoryBoxesValid(),
                "All history items should have valid structure"
        );

        logger.info("TC-HIS-001 - User can see all history");
    }
}