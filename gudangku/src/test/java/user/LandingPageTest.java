package user;

import core.BaseTest;

import core.DriverManager;
import core.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.user.LandingPage;
import org.example.user.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LandingPageTest.class);

    @Test(priority = 2, groups = {"e2e"}, description = "User can see their account summary", retryAnalyzer = core.RetryAnalyzer.class)
    public void testUserCanSeeTheirAccountSummary() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        LandingPage landingPage = new LandingPage(DriverManager.getDriver());

        logger.info("Step 1: User login with valid credentials");
        Object[][] data = TestUtils.getTestData(System.getProperty("user.dir") + "/src/test/resources/data/login-data-test.xlsx", "login-tests");
        String username = data[0][0].toString();
        String password = data[0][1].toString();

        loginPage.login(username, password);
        Assert.assertTrue(landingPage.verifyRedirectToLanding(), "User should be redirected to landing page after login");

        logger.info("Step 2: Verify all dashboard stats are visible and have valid value");
        Assert.assertTrue(landingPage.isDashboardStatsHaveValidValue(), "All value stats should be visible and have valid value");

        logger.info("TC-STS-001 - User can see their account summary");
    }
}
