package PHPTravels;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PHPTravelsRegistration {

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPasswordField;

    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement inputPasswordConfField;

    @FindBy(xpath = "//input[@placeholder='Mobile Number']")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement sumitButton;


    @FindBy(xpath = "//div[@class='alert alert-danger']//p[.='The Email field must contain a valid email address.']")
    private WebElement emailError;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p[.='The Password field is required.']")
    private WebElement passwordError;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p[.='The First name field is required.']")
    private WebElement firstNameError;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p[.='The Last Name field is required.']")
    private WebElement lastNameError;

    public WebElement getEmailError() {
        return emailError;

    }

    public WebElement getPasswordError() {
        return passwordError;
    }

    public WebElement getFirstNameError() {
        return firstNameError;
    }

    public WebElement getLastNameError() {
        return lastNameError;
    }

    private WebDriver webDriver;

    public PHPTravelsRegistration(final WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    final static Logger logger = Logger.getLogger(PHPTravelsRegistration.class);

    public PHPTravelsAccount submitButton() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,30)");
        sumitButton.click();
        logger.info("Submit button was clicked");
        return new PHPTravelsAccount(webDriver);

    }

    public void setFirstName(String name) {
        firstNameField.sendKeys(name);
        logger.info("first name '" + name + "' has been set");
    }

    public void setLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        logger.info("last name '" + lastName + "' has been set");
    }

    public void setInputPassword(String password) {
        inputPasswordField.sendKeys(password);
        inputPasswordConfField.sendKeys(password);
        logger.info("Password and Confirmation (" + password + ") has been set");
    }

    public void setMobileNumber(String phoneNumber) {
        mobileNumberField.sendKeys(phoneNumber);
        logger.info("phone number (" + phoneNumber + ") has been set");
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
        logger.info("unique login '" + email + "' has been set");
    }

    private double generateRandom(double min, double max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public String generateEmail() {
        double rand = generateRandom(0, 999);
        String email = "fkg7@" + rand + ".com";
        logger.info("unique login '" + email + "' has been set");
        return email;
    }
}
