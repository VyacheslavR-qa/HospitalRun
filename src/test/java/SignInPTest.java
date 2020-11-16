import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.PatientListingPage;
import pages.SignInPage;

public class SignInPTest {

    static final String USERNAME_1 = "hr.doctor@hospitalrun.io";
    static final String PASSWORD_1 = "HRt3st12";
    WebDriver driver;

    @Before
    public void setDriver() {
        driver = new Driver(System.getProperty("browser")).getDriver();
    }

    @Test
    public void signInWithCorrectCredentials() {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.openPage();

        signInPage.setUsername(USERNAME_1);
        signInPage.setPassword(PASSWORD_1);
        signInPage.clickSignInButton();

        PatientListingPage patientListingPage = new PatientListingPage(driver);
        Assert.assertTrue(patientListingPage.isPageOpened());
        Assert.assertTrue(patientListingPage.isUserLoggedIn());
    }

    @After
    public void close() {
        driver.close();
    }

}
