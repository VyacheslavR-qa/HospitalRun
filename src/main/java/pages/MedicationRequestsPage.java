package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MedicationRequestsPage {

    private final String PAGE_TITLE = "Medication Requests";
    private final String REQUESTS_ITEM_TEXT = "Requests";
    private final String COMPLETED_ITEM_TEXT = "Completed";
    private final String NEW_REQUEST_ITEM_TEXT = "New Request";
    private final String RETURN_MEDICATION_ITEM_TEXT = "Return Medication";
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "/html/body/div/nav/div/div[2]/div[5]/div[2]/div[1]/a")
    private WebElement diRequests;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ember765\"]/div[2]/div[2]")
    private WebElement diCompleted;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ember765\"]/div[2]/div[3]")
    private WebElement diNewRequest;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ember765\"]/div[2]/div[5]")
    private WebElement diReturnMedication;

    public MedicationRequestsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openNewRequestPage() {
        diNewRequest.click();
    }

    public boolean isRequestsItemAvailable() {
        String itemText = diRequests.getAttribute("innerHTML");
        if (itemText.contains(REQUESTS_ITEM_TEXT)) {
            return true;
        } else {
            System.out.println("Item text is " + itemText);
            return false;
        }
    }

    public boolean isCompletedItemAvailable() {
        String itemText = diCompleted.getAttribute("innerHTML");
        if (itemText.contains(COMPLETED_ITEM_TEXT)) {
            return true;
        } else {
            System.out.println("Item text is " + itemText);
            return false;
        }
    }

    public boolean isNewRequestItemAvailable() {
        String itemText = diNewRequest.getAttribute("innerHTML");
        if (itemText.contains(NEW_REQUEST_ITEM_TEXT)) {
            return true;
        } else {
            System.out.println("Item text is " + itemText);
            return false;
        }
    }

    public boolean isReturnMedicationItemAvailable() {
        String itemText = diReturnMedication.getAttribute("innerHTML");
        if (itemText.contains(RETURN_MEDICATION_ITEM_TEXT)) {
            return true;
        } else {
            System.out.println("Item text is " + itemText);
            return false;
        }
    }

}
