package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MedicationNewRequestPage {

    private static final String AC_VISIT_X_PATH_WITH_NO_ID = "//*[@id=\"visit-ember3121\"]/option[";
    private static final String AC_VISIT_LIST_X_PATH = "//*[@id=\"visit-ember3121\"]";
    private static final String AC_VISIT_LIST_CSS_SELECTOR = "#visit-ember2352";
    private static final String AC_MEDICATION_X_PATH = "//*[@id=\"ember3131\"]/span/div";
    private static final String AC_MEDICATION_CSS_SELECTOR = "#ember2378 > span > div > div";
    private static final String MEDICATION_REQUEST_SAVED_DIALOG_TITLE = "Medication Request Saved";
    private static final String PAGE_TITLE = "New Medication Request";
    private static final String medicationRequestSavedDialogTitleXPath = "/html/body/div[1]/div[2]/div/div/div/div[1]/h4";
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[1]/div[1]/h1")
    private WebElement tvPageTitle;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div/span/div")
    private WebElement acPatient;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[1]/div[1]/div/span/input[2]")
    private WebElement etPatient;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[1]/div[2]/div/select")
    private WebElement ddVisit;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[2]/div/span/input[2]")
    private WebElement etMedication;
    @FindBy(how = How.XPATH, using = "//*[@id=\"prescription-ember2406\"]")
    private WebElement etPrescription;
    @FindBy(how = How.XPATH, using = "//*[@id=\"display_prescriptionDate-ember2429\"]")
    private WebElement etPrescriptionDate;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[5]/div[1]/div/input")
    private WebElement etQuantityRequested;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[2]/div/div/div[1]/form/div[5]/div[2]/div/input")
    private WebElement etRefills;
    @FindBy(how = How.XPATH, using = "//*[@id=\"ember2270\"]/div/div[2]/button[2]")
    private WebElement btAdd;
    @FindBy(how = How.XPATH, using = medicationRequestSavedDialogTitleXPath)
    private WebElement tvMedicationRequestSavedDialogTitle;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div/div[3]/button")
    private WebElement btMedicationRequestSavedDialogOk;
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div/div[1]/button")
    private WebElement btMedicationRequestSavedDialogClose;

    public MedicationNewRequestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        String titleText = tvPageTitle.getAttribute("innerHTML");
        if (titleText.equals(PAGE_TITLE)) {
            return true;
        } else {
            System.out.println("Page title text is " + titleText);
            return false;
        }
    }

    public void setPatientText(String text, WebDriverWait wait) throws InterruptedException {
        etPatient.clear();
        etPatient.click();
        Thread.sleep(3000);
        etPatient.sendKeys(text.substring(0, text.length() - 1));
        etPatient.click();
        Thread.sleep(2000);
        etPatient.sendKeys(text.substring(text.length() - 1));
        //wait.until(ExpectedConditions.visibilityOf(acPatient));
        wait.until(ExpectedConditions.visibilityOf(acPatient));
    }

    public void setPatientAutocomplete(String patientName) {
        List<WebElement> acPatients = driver.findElements(By.cssSelector("#ember2343 > span > div > div > div"));
        for (WebElement acItem : acPatients) {
            if (acItem.getText().equals(patientName)) {
                acItem.click();
                return;
            } /*else {
                System.out.println("acItem text " + acItem.getText());
            }*/
        }
        System.out.println("Patient not found: " + patientName);
    }

    public void setRandomVisit(WebDriverWait wait) throws InterruptedException {
        ddVisit.click();
        WebElement acVisitList = driver.findElement(By.cssSelector(AC_VISIT_LIST_CSS_SELECTOR));
        Thread.sleep(5000);
        Select select = new Select(acVisitList);
        List<WebElement> visitOptions = select.getOptions();
        visitOptions.get(random(1, visitOptions.size())).click();
    }

    public void setMedicationText(String text) throws InterruptedException {
        etMedication.click();
//        etMedication.clear();
        Thread.sleep(5000);
        etMedication.sendKeys(text);
    }

    public void setRandomMedication() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> acMedicationList = driver.findElements(By.cssSelector(AC_MEDICATION_CSS_SELECTOR));
        acMedicationList.get(random(0, acMedicationList.size())).click();
    }

    public void setPrescriptionText(String text) {
        etPrescription.clear();
        etPrescription.sendKeys(text);
    }

    public void setPrescriptionDateBy(int dayDifference) throws InterruptedException {
        LocalDate date = LocalDate.now().plusDays(dayDifference);
        etPrescriptionDate.clear();
        etPrescriptionDate.sendKeys(date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Thread.sleep(5000);
    }

    public void setRandomQuantity(int lowestQuantity, int highestQuantity) {
        etQuantityRequested.clear();
        etQuantityRequested.click();
        etQuantityRequested.sendKeys(String.valueOf(random(lowestQuantity, highestQuantity)));
    }

    public void setRandomRefills(int lowestQuantity, int highestQuantity) {
        etRefills.clear();
        etRefills.sendKeys(String.valueOf(random(lowestQuantity, highestQuantity)));
    }

    public void clickAddButton() {
        btAdd.click();
    }

    public boolean isMedicationRequestSavedDialogOpened() throws InterruptedException {
        Thread.sleep(2000);
        if (driver.findElements(By.xpath(medicationRequestSavedDialogTitleXPath)).size() == 0) {
            return false;
        }
        String titleText = tvMedicationRequestSavedDialogTitle.getAttribute("innerHTML");
        if (titleText.equals(MEDICATION_REQUEST_SAVED_DIALOG_TITLE)) {
            return true;
        } else {
            System.out.println("Title text is " + titleText);
            return false;
        }
    }

    public boolean isMedicationRequestSavedDialogOkButtonAvailable() {
        return (btMedicationRequestSavedDialogOk.isDisplayed());
    }

    public boolean isMedicationRequestSavedDialogCloseButtonAvailable() {
        return (btMedicationRequestSavedDialogClose.isDisplayed());
    }

    public void clickMedicationRequestSavedDialogOkButton() {
        btMedicationRequestSavedDialogOk.click();
    }


    /**
     * Returns a random int from a range
     *
     * @param lowestNumber
     * @param highestNumber
     * @return
     */
    private int random(int lowestNumber, int highestNumber) {
        return (int) (lowestNumber + Math.random() * (highestNumber - lowestNumber));
    }
}
