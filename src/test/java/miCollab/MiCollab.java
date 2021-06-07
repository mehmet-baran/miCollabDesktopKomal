package miCollab;

import miCollab.utilities.CommonSteps;
import miCollab.utilities.ConfigurationReader;
import miCollab.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.time.LocalTime;


public class MiCollab extends CommonSteps {

    @Test
    public void incomingCallTest() {
        int testDurationInMinutes = 420;
        int callDurationInSeconds = 20;
        licensePage.acceptButton.click();
        loginPage.usernameTextbox.sendKeys(ConfigurationReader.get("loginId1"));
        loginPage.passwordTextbox.sendKeys(ConfigurationReader.get("loginPassword1"));
        loginPage.loginButton.click();
        waitFor(1);
        dashboardPage.skipButton.click();
        dashboardPage.clickOnButton("OK");
        waitFor(2);
        LocalTime startTime = LocalTime.now();
        LocalTime finalTime = startTime.plus(Duration.ofMinutes(testDurationInMinutes));
        int numberOfCalls = 0;
        dashboardPage.goToLeftMenuOption("Settings");
        dashboardPage.changeSettingsOf("Manage Hotkeys");
        Actions actions = new Actions(driver);
        waitFor(2);
        waitForVisibility(dashboardPage.acceptCallHotKey, 10);
        dashboardPage.acceptCallHotKey.click();
        dashboardPage.clickOnButton("Record Hotkey");
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.NUMPAD1).perform();
        dashboardPage.clickOnButton("Save");
        waitFor(2);
        dashboardPage.endCallHotkey.click();
        dashboardPage.clickOnButton("Record Hotkey");
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.NUMPAD2).perform();
        dashboardPage.clickOnButton("Save");
        dashboardPage.goToLeftMenuOption("Home");
        int cycleCount=0;
        while (Duration.between(finalTime, LocalTime.now()).getSeconds() < 0) {
            outer:
            if (dashboardPage.isIncomingCallAvailable()) {
                waitFor(1);
                dashboardPage.acceptCallButton.click();
                LocalTime callStartTime = LocalTime.now();
                LocalTime callfinalTime = callStartTime.plus(Duration.ofSeconds(callDurationInSeconds));
                while (Duration.between(callfinalTime, LocalTime.now()).getSeconds() < 0) {
                    if (!dashboardPage.doesCallContinue()) {
                        break outer;
                    }
                    waitFor(1);
                }
                if (dashboardPage.isEndCallButtonAvailable()) {
                    actions.keyDown(Keys.CONTROL).sendKeys(Keys.NUMPAD2).perform();
                }
            }
            cycleCount++;
            if(cycleCount%2==1){
                dashboardPage.goToLeftMenuOption("Home");
            }
            if(cycleCount%2==0) {
                dashboardPage.goToLeftMenuOption("Home");
            }
        }
    }


    @BeforeTest
    public void create() {
        Driver.setUp();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
