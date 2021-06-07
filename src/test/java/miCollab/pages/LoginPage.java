package miCollab.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonPageElements {
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    public WebElement usernameTextbox;

    @FindBy(name = "password")
    public WebElement passwordTextbox;

    @FindBy(xpath = "//div[@class='button-content']")
    public WebElement loginButton;


}
