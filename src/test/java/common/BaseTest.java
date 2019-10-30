package common;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;

    public  WebDriver getDriver() {
        return driver;
    }

    private static final Long ELEMENT_WAIT_TIME_OUT = 30L;

    public  Long getWait() {
        return ELEMENT_WAIT_TIME_OUT;
    }

    @Before
    public  void initWebDriver() {
        System.setProperty("webdriver.firefox.driver", "gecodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public  void driverTearDown() {
        driver.close();
        System.out.println();
    }
}
