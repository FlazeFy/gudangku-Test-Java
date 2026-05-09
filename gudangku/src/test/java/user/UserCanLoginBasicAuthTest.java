package user;

import core.BaseTest;
import core.DriverManager;
import core.TestUtils;
import org.example.user.UserCanLoginBasicAuth;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserCanLoginBasicAuthTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(UserCanLoginBasicAuthTest.class);

    @Test(priority = 1, groups = {"e2e"}, description = "User can login with valid data", retryAnalyzer = core.RetryAnalyzer.class)
    public void testUserCanLoginWithValidData() {
        UserCanLoginBasicAuth loginPage = new UserCanLoginBasicAuth(DriverManager.getDriver());

        logger.info("Step 1: User open the login page");
        // navigation handled by BaseTest.setUp()

        logger.info("Step 2: User fill the login form");
        Object[][] data = TestUtils.getTestData(
                System.getProperty("user.dir") + "/src/test/resources/data/login-data-test.xlsx",
                "login-tests"
        );
        String username = data[0][0].toString();
        String password = data[0][1].toString();
        loginPage.login(username, password);

        logger.info("Step 3: User click the submit button");
        Assert.assertTrue(loginPage.verifyRedirectToLanding(), "User should be redirected to landing page after login");

        logger.info("TC-AU-001 - User can login with valid data - PASSED");
    }
}
