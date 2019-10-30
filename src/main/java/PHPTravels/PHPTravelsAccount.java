package PHPTravels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PHPTravelsAccount {

    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//img[@src='https://www.phptravels.net/uploads/global/default/user.png']")
    private WebElement image;

    public WebElement getImage() {
        return image;
    }

    public PHPTravelsAccount(final WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }
}