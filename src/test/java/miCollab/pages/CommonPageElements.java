package miCollab.pages;

import miCollab.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonPageElements extends Driver {
    public CommonPageElements() {
        PageFactory.initElements(driver,this);
    }



    public void clickOnButton(String buttonText){
        driver.findElement(By.xpath("//div[@class='button-content'][.='"+buttonText+"']")).click();
    }

    public void goToLeftMenuOption(String option){
        driver.findElement(By.xpath("//li[starts-with(@class,'left-menu-li')]//span[.='"+option+"']")).click();
    }

    public void changeSettingsOf(String option){
        driver.findElement(By.xpath("//div[@class='mtl-li-content'][.='"+option+"']/..")).click();
    }

    public WebElement getHotkey(String hotkeyText){
        return driver.findElement(By.xpath("//div[@class='hotkey-title'][.='"+hotkeyText+"']"));
    }

}

