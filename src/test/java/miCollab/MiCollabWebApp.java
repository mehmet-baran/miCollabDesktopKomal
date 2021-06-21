package miCollab;

import miCollab.utilities.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;

public class MiCollabWebApp extends CommonSteps {

    @Test
    public void incomingCallTest() throws UnknownHostException, InterruptedException {
        int testDurationInDays = 30;

        licensePage.acceptButton.click();
        loginPage.usernameTextbox.sendKeys(loginPage.getCredentials()[0]);
        loginPage.passwordTextbox.sendKeys(loginPage.getCredentials()[1]);
        loginPage.loginButton.click();
        waitFor(1);
        dashboardPage.skipButton.click();
        dashboardPage.clickOnButton("OK");
        waitFor(2);
        LocalDateTime finalTime = LocalDateTime.now().plus(Duration.ofDays(testDurationInDays));
        int numberOfCalls = 0;
        int cycleCount=0;
        dashboardPage.goToLeftMenuOption("Settings");
        dashboardPage.changeSettingsOf("Manage Hotkeys");
        Actions actions= new Actions(driver);
        waitFor(2);
        waitForVisibility(dashboardPage.acceptCallHotKey, 10);
        dashboardPage.acceptCallHotKey.click();
        dashboardPage.clickOnButton("Record Hotkey");
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.NUMPAD1).perform();
        actions.release().sendKeys(Keys.CONTROL).perform();
        dashboardPage.clickOnButton("Save");
        dashboardPage.goToLeftMenuOption("Home");


        while (LocalDateTime.now().isBefore(finalTime)) {
            outer:
            if (dashboardPage.isIncomingCallAvailable()) {
                actions.keyDown(Keys.CONTROL).sendKeys(Keys.NUMPAD1).perform();
                numberOfCalls++;
                while (dashboardPage.isEndCallButtonAvailable()) {
                    if (!dashboardPage.doesCallContinue()) {
                        break outer;
                    }
                    waitFor(1);
                }
            }
            cycleCount++;
            if(dashboardPage.isControlElementAvailable()){
                break;
            }
            if(cycleCount%3==0) {
                dashboardPage.goToLeftMenuOption("Home");
            }
        }
        dashboardPage.goToLeftMenuOption("Call History");
        dashboardPage.writeTestData();

        System.out.println("numberOfCalls = " + numberOfCalls);

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
