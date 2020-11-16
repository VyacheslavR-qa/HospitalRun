package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PatientListingPage {

    private static final String PAGE_TITLE = "Patient Listing";
    private static final String LOGOUT_BUTTON_TEXT = "Logout";
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[@id=\"ember412\"]/div/div[1]/div[1]/h1")
    private WebElement tvPageTitle;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ember412\"]/nav/header/nav/a[1]")
    private WebElement btLogout;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ember412\"]/nav/header/a[2]")
    private WebElement btSettings;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ember767\"]")
    private WebElement ddMedication;

    public PatientListingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return (tvPageTitle.getText().equals(PAGE_TITLE));
    }

    public boolean isUserLoggedIn() {
        openSettings();
        return (btLogout.getText().equals(LOGOUT_BUTTON_TEXT));
    }

    private void openSettings() {
        btSettings.click();
    }

    public void logout() {
        openSettings();
        btLogout.click();
    }

    public void openMedicationPage() {
        ddMedication.click();
    }
}
