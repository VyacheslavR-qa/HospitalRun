import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SignInPage;

public class SignInInvalidCredentialsNTest {

    static final String USERNAME_INVALID_1 = "invalid username";
    static final String PASSWORD_INVALID_1 = "invalid password";
    WebDriver driver;

    @Before
    public void setDriver() {
        driver = new Driver(System.getProperty("browser")).getDriver();
    }

    @Test
    public void signInWithCorrectCredentials() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.openPage();

        signInPage.setUsername(USERNAME_INVALID_1);
        signInPage.setPassword(PASSWORD_INVALID_1);
        signInPage.clickSignInButton();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ember412 > div > form > div.signin-contents > div.alert.alert-danger.form-signin-alert")));

        Assert.assertTrue(signInPage.isSignInErrorDisplayed());
    }

    @After
    public void close() {
        driver.close();
    }

}
