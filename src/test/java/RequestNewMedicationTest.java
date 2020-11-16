import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MedicationNewRequestPage;
import pages.MedicationRequestsPage;
import pages.PatientListingPage;
import pages.SignInPage;

public class RequestNewMedicationTest {

    private static final String USERNAME_1 = "hr.doctor@hospitalrun.io";
    private static final String PASSWORD_1 = "HRt3st12";
    private static final String PATIENT_PARTIAL = "Test Patient";
    private static final String PATIENT_FULL = "Test Patient - P00201";
    private static final String PATIENT_SELECTOR = "#ember2340 > span > div > div > div:nth-child(4)";
    private static final String MEDICATION_PARTIAL = "Pramoxine";
    private static final String PRESCRIPTION_FULL = "Testing prescription";
    private static final int QUANTITY_LOWEST = 1;
    private static final int QUANTITY_HIGHEST = 5;
    private static final int REFILLS_LOWEST = 5;
    private static final int REFILLS_HIGHEST = 10;
    WebDriver driver;

    @Before
    public void setDriver() {
        driver = new Driver(System.getProperty("browser")).getDriver();
    }

    @Test
    public void signInWithCorrectCredentials() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        // Login
        SignInPage signInPage = new SignInPage(driver);
        signInPage.openPage();
        signInPage.setUsername(USERNAME_1);
        signInPage.setPassword(PASSWORD_1);
        signInPage.clickSignInButton();
        //Open Medication page
        PatientListingPage patientListingPage = new PatientListingPage(driver);
        Assert.assertTrue(patientListingPage.isPageOpened());
        Assert.assertTrue(patientListingPage.isUserLoggedIn());
        patientListingPage.openMedicationPage();
        // Open New Medication Request page
        MedicationRequestsPage medicationRequestsPage = new MedicationRequestsPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ember765 > div.category-sub-items > div:nth-child(1)")));
        Assert.assertTrue(medicationRequestsPage.isRequestsItemAvailable());
        Assert.assertTrue(medicationRequestsPage.isCompletedItemAvailable());
        Assert.assertTrue(medicationRequestsPage.isNewRequestItemAvailable());
        Assert.assertTrue(medicationRequestsPage.isReturnMedicationItemAvailable());
        medicationRequestsPage.openNewRequestPage();
        // Request new medication
        MedicationNewRequestPage medicationNewRequestPage = new MedicationNewRequestPage(driver);
        medicationNewRequestPage.setPatientText(PATIENT_PARTIAL, wait);
        medicationNewRequestPage.setPatientAutocomplete(PATIENT_FULL);
        medicationNewRequestPage.setRandomVisit(wait);
        medicationNewRequestPage.setMedicationText(MEDICATION_PARTIAL);
        medicationNewRequestPage.setRandomMedication();
        medicationNewRequestPage.setPrescriptionText(PRESCRIPTION_FULL);
        medicationNewRequestPage.setPrescriptionDateBy(-1);
        medicationNewRequestPage.setRandomQuantity(QUANTITY_LOWEST, QUANTITY_HIGHEST);
        medicationNewRequestPage.setRandomRefills(REFILLS_LOWEST, REFILLS_HIGHEST);
        medicationNewRequestPage.clickAddButton();
        // Deal with "Medication Request Saved" dialog
        Assert.assertTrue(medicationNewRequestPage.isMedicationRequestSavedDialogOpened());
        Assert.assertTrue(medicationNewRequestPage.isMedicationRequestSavedDialogOkButtonAvailable());
        Assert.assertTrue(medicationNewRequestPage.isMedicationRequestSavedDialogCloseButtonAvailable());
        medicationNewRequestPage.clickMedicationRequestSavedDialogOkButton();
        Assert.assertFalse(medicationNewRequestPage.isMedicationRequestSavedDialogOpened());
        Assert.assertTrue(medicationNewRequestPage.isPageOpened());
    }

    @After
    public void close() {
        driver.close();
    }
}
