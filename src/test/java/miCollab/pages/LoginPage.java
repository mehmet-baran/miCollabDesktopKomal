package miCollab.pages;

import miCollab.utilities.ExcelUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

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

    public String[] getCredentials() throws UnknownHostException {

        InetAddress ip = InetAddress.getLocalHost();
        String[] split = ip.toString().split("/");
        String ipAddress=split[split.length-1];

        String [] usernamePassword=new String[2];
        ExcelUtil credentialTable =new ExcelUtil("src/test/resources/credentials.xlsx", "Sheet1");
        String[][] dataArray = credentialTable.getDataArrayWithoutFirstRow();
        for (int i = 0; i < credentialTable.rowCount()-1; i++) {
            if(dataArray[i][0].equals(ipAddress)){
                usernamePassword[0]=dataArray[i][1];
                usernamePassword[1]=dataArray[i][2];
            }
        }
        return usernamePassword;
    }

}
