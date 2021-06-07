package miCollab.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LicensePage extends CommonPageElements {
    public LicensePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='button-content']")
    public WebElement acceptButton;

}
