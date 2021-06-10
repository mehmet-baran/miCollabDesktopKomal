package miCollab.pages;

import miCollab.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonPageElements extends Driver {
    public CommonPageElements() {
        PageFactory.initElements(driver,this);
    }

}

