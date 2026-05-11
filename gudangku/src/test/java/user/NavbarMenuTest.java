package user;

import core.BaseTest;
import core.DriverManager;
import core.TestUtils;
import org.example.user.LandingPage;
import org.example.user.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NavbarMenuTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(NavbarMenuTest.class);

    @Test(priority = 2, groups = {"e2e"}, description = "User can see and access menu using navigation menu button", retryAnalyzer = core.RetryAnalyzer.class)
    public void testUserCanSeeAndAccessMenuUsingNavigationMenuButton() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        LandingPage landingPage = new LandingPage(DriverManager.getDriver());

        logger.info("Step 1: User login with valid credentials");
        Object[][] data = TestUtils.getTestData(System.getProperty("user.dir") + "/src/test/resources/data/login-data-test.xlsx",
                "login-tests"
        );
        String username = data[0][0].toString();
        String password = data[0][1].toString();

        loginPage.login(username, password);
        Assert.assertTrue(
                landingPage.verifyRedirectToLanding(),
                "User should be redirected to landing page after login"
        );

        logger.info("Step 2: Verify all navigation menu buttons are visible");
        Assert.assertTrue(
                landingPage.isAllMenuButtonsVisible(),
                "All navigation menu buttons should be visible"
        );

        logger.info("Step 3: Verify all navigation menu buttons have correct href");
        Assert.assertTrue(
                landingPage.isAllMenuButtonsHaveHref(),
                "All navigation menu buttons should have correct href"
        );

        logger.info("Step 4: User accesses menu using navigation buttons");
        landingPage.clickMenuButtonChat();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/chat"),
                "User should be redirected to Chat page"
        );
        DriverManager.getDriver().navigate().back();

        landingPage.clickMenuButtonInventory();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/inventory"),
                "User should be redirected to Inventory page"
        );
        DriverManager.getDriver().navigate().back();

        landingPage.clickMenuButtonStats();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/stats"),
                "User should be redirected to Stats page"
        );
        DriverManager.getDriver().navigate().back();

        landingPage.clickMenuButtonCalendar();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/calendar"),
                "User should be redirected to Calendar page"
        );
        DriverManager.getDriver().navigate().back();

        landingPage.clickMenuButtonReport();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/report"),
                "User should be redirected to Report page"
        );
        DriverManager.getDriver().navigate().back();

        landingPage.clickMenuButtonHistory();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/history"),
                "User should be redirected to History page"
        );
        DriverManager.getDriver().navigate().back();

        landingPage.clickMenuButtonProfile();
        Assert.assertTrue(
                DriverManager.getDriver().getCurrentUrl().contains("/profile"),
                "User should be redirected to Profile page"
        );

        logger.info("TC-NAV-001 - User can see and access menu using navigation menu button - PASSED");
    }
}