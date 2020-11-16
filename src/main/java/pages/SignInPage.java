package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    private static final String PAGE_URL = "http://demo.hospitalrun.io/#/login";
    private static final String SIGN_IN_ERROR_TEXT = "Username or password is incorrect.";
    private static final String SIGN_IN_FORM_TITLE = "please sign in";
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[@id=\"identification\"]")
    private WebElement etUsername;

    @FindBy(how = How.XPATH, using = "//*[@id=\"password\"]")
    private WebElement etPassword;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/form/div[2]/button")
    private WebElement btSignIn;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/form/div[2]/div[1]")
    private WebElement tvSignInError;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/form/div[2]/h2")
    private WebElement tvSignInFormTitle;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(PAGE_URL);
    }

    public void clickSignInButton() {
        btSignIn.click();
    }

    public void setUsername(String username) {
        etUsername.clear();
        etUsername.sendKeys(username);
    }

    public void setPassword(String password) {
        etPassword.clear();
        etPassword.sendKeys(password);
    }

    public boolean isSignInErrorDisplayed() {
        String actualErrorMessage = tvSignInError.getAttribute("innerHTML");
        if (actualErrorMessage.equals(SIGN_IN_ERROR_TEXT)) {
            return true;
        } else {
            System.out.println("Sign in error text is " + actualErrorMessage);
            return false;
        }
    }

    public boolean isPageOpened() {
        String signInFormTitle = tvSignInFormTitle.getAttribute("innerHTML");
        if (signInFormTitle.equals(SIGN_IN_FORM_TITLE)) {
            return true;
        } else {
            System.out.println("Sign in form title is " + signInFormTitle);
            return false;
        }
    }

}
