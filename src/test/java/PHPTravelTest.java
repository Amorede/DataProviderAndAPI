import PHPTravels.PHPTravelsAccount;
import PHPTravels.PHPTravelsRegistration;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class PHPTravelTest {

    private WebDriver driver;
    private final static Logger logger = Logger.getLogger(PHPTravelsRegistration.class);


    @BeforeTest
    public void setDriver() {
        System.setProperty("webdriver.firefox.driver", "gecodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        logger.info("Browser is opened");
        String log4jConfPath = "D://genesis/src/main/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
    }

    @AfterTest
    public void driverClose() {
        driver.close();
        logger.info("Driver was closed");
    }

    @Test(dataProvider = "getPartData")
    public void phpTravelRegistrationTest(String firstName, String lastName, String password, String email, String phoneNumber) {
        driver.get("https://www.phptravels.net/register");
        logger.info("'https://www.phptravels.net/register' link is opened");

        PHPTravelsRegistration PHPTravelsRegistration = new PHPTravelsRegistration(driver);

        PHPTravelsRegistration.setFirstName(firstName);
        PHPTravelsRegistration.setLastName(lastName);
        PHPTravelsRegistration.setMobileNumber(phoneNumber);
        PHPTravelsRegistration.setEmail(email);
        PHPTravelsRegistration.setInputPassword(password);
        PHPTravelsRegistration.submitButton();

        if(firstName.equals("")) {
            WebElement firstNameError = PHPTravelsRegistration.getFirstNameError();
            Assert.assertTrue(firstNameError.isDisplayed());
        }
        if(lastName.equals("")) {
            WebElement lastNameError = PHPTravelsRegistration.getLastNameError();
            Assert.assertTrue(lastNameError.isDisplayed());
        }
        if(email.equals("")) {
            WebElement emailError = PHPTravelsRegistration.getEmailError();
            Assert.assertTrue(emailError.isDisplayed());
        }
        if(password.equals("")) {
            WebElement passwordError = PHPTravelsRegistration.getPasswordError();
            Assert.assertTrue(passwordError.isDisplayed());
        }
    }

    @Test
    public void checkValidRegistration() throws InterruptedException {
        driver.get("https://www.phptravels.net/register");
        logger.info("'https://www.phptravels.net/register' link is opened");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        PHPTravelsRegistration phpTravelsRegistration = new PHPTravelsRegistration(driver);
        PHPTravelsAccount phpTravelsAccount = new PHPTravelsAccount(driver);
        WebElement image = phpTravelsAccount.getImage();

        String firstName ="Robert";
        String lastName = "Smith";
        String password = "password999";
        String email = phpTravelsRegistration.generateEmail();


        phpTravelsRegistration.setFirstName(firstName);
        phpTravelsRegistration.setLastName(lastName);
        phpTravelsRegistration.setEmail(email);
        phpTravelsRegistration.setInputPassword(password);
        phpTravelsRegistration.submitButton();

        wait.until(ExpectedConditions.elementToBeClickable(image));

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://www.phptravels.net/account/");
    }

    @DataProvider
    public Object[][] getPartData() {
        return new Object[][]{
                {"", "Smith", "0666666666", "Email@google.com", "password999"},
                {"John", "", "0666666666", "Email@google.com", "password999"},
                {"John", "Smith", "0666666666", "Wrong", "password999"},
                {"John", "Smith", "0666666666", "Email@google.com", ""}
        };
    }
}
