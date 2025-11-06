package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");

    private By genderMale = By.xpath("//label[text()='Male']");
    private By genderFemale = By.xpath("//label[text()='Female']");
    private By genderOther = By.xpath("//label[text()='Other']");

    private By mobile = By.id("userNumber");
    private By subjects = By.id("subjectsInput");

    private By hobbiesSports  = By.xpath("//label[text()='Sports']");
    private By hobbiesReading = By.xpath("//label[text()='Reading']");
    private By hobbiesMusic   = By.xpath("//label[text()='Music']");

    private By uploadPicture = By.id("uploadPicture");
    private By address = By.id("currentAddress");
    private By state = By.id("react-select-3-input");
    private By city  = By.id("react-select-4-input");

    private By submitButton = By.id("submit");
    private By successModal = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");

        ((JavascriptExecutor) driver).executeScript("""
            document.querySelector('#fixedban')?.remove();
            document.querySelector('footer')?.remove();
        """);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");
    }

    public void fillBasicInfo(String fName, String lName, String userEmail, String gender, String phone) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstNameField);

        firstNameField.sendKeys(fName);
        type(lastName, lName);
        type(email, userEmail);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 150)");

        clickGender(gender);
        type(mobile, phone);
    }

    public void fillAdditionalInfo(String subject, String hobby, String addressText, String stateName, String cityName) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400)");

        type(subjects, subject);
        driver.findElement(subjects).sendKeys(Keys.ENTER);

        selectHobbies(hobby);

        type(address, addressText);

        WebElement stateInput = wait.until(ExpectedConditions.elementToBeClickable(state));
        stateInput.sendKeys(stateName);
        stateInput.sendKeys(Keys.ENTER);

        WebElement cityInput = wait.until(ExpectedConditions.elementToBeClickable(city));
        cityInput.sendKeys(cityName);
        cityInput.sendKeys(Keys.ENTER);
    }

    public void uploadImage(String filePath) {
        WebElement upload = wait.until(ExpectedConditions.elementToBeClickable(uploadPicture));
        upload.sendKeys(filePath);
    }

    public void submitForm() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(submitButton));
        click(submitButton);
    }

    public boolean isSuccessModalVisible() {
        return isElementVisible(successModal);
    }


    private void clickGender(String gender) {
        By target;
        if ("male".equalsIgnoreCase(gender)) target = genderMale;
        else if ("female".equalsIgnoreCase(gender)) target = genderFemale;
        else target = genderOther;

        WebElement element = driver.findElement(target);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -80)");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(target)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void selectHobbies(String hobbyRaw) {
        if (hobbyRaw == null || hobbyRaw.isBlank()) return;

        String[] parts = hobbyRaw.split("\\s*[,;|]\\s*");
        Arrays.stream(parts).forEach(h -> {
            By locator = switch (h.trim().toLowerCase()) {
                case "sports" -> hobbiesSports;
                case "reading" -> hobbiesReading;
                case "music" -> hobbiesMusic;
                default -> null;
            };
            if (locator != null) {
                try {
                    click(locator);
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                            driver.findElement(locator));
                }
            }
        });
    }
}
