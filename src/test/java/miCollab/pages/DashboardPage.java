package miCollab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage extends CommonPageElements {
    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[.='Skip'])[2]")
    public WebElement skipButton;

    @FindBy(xpath = "(//div[@data-vn='inboundCall']//div[starts-with(@class,'cust-button-content')])[2]")
    public WebElement acceptCallButton;

    @FindBy(xpath = "//div[@class='flex-btn incall-end-btn tappable']")
    public WebElement endCallButton;

    @FindBy(css = "[data-vn=\"dnBarcode\"]")
    public WebElement barcode;

    @FindBy(css ="[data-hotkey-id='acceptCall']")
    public WebElement acceptCallHotKey;

    @FindBy(css = "[data-hotkey-id='declineCall']")
    public WebElement endCallHotkey;

    @FindBy(xpath = "(//div[@class=\"header-btn tappable\"])[6]")
    public WebElement controlElement;




    public boolean isIncomingCallAvailable() {
        List<WebElement> incomingCallList = driver.findElements(By.xpath("(//div[@data-vn='inboundCall']//div[starts-with(@class,'cust-button-content')])[2]"));
        return incomingCallList.size() != 0;
    }

    public boolean isEndCallButtonAvailable() {
        List<WebElement> endCallList = driver.findElements(By.xpath("//div[@class='flex-btn incall-end-btn tappable']"));
        return endCallList.size() != 0;
    }

    public boolean doesCallContinue(){
        List<WebElement> controlElements = driver.findElements(By.xpath("(//div[@class=\"header-btn tappable\"])[6]"));
        return controlElements.size() == 0;
    }

}
