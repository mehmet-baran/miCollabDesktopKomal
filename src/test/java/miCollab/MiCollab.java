package miCollab;

import miCollab.utilities.CommonSteps;
import miCollab.utilities.ConfigurationReader;
import miCollab.utilities.Driver;
import miCollab.utilities.ExcelUtil;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MiCollab extends CommonSteps {

    @Test
    public void incomingCallTest() throws InterruptedException {
        int testDurationInDays = 30;
        licensePage.acceptButton.click();
        loginPage.usernameTextbox.sendKeys(ConfigurationReader.get("loginId1"));
        loginPage.passwordTextbox.sendKeys(ConfigurationReader.get("loginPassword1"));
        loginPage.loginButton.click();
        waitFor(1);
        dashboardPage.skipButton.click();
        dashboardPage.clickOnButton("OK");
        waitFor(2);
        LocalDateTime finalTime = LocalDateTime.now().plus(Duration.ofDays(testDurationInDays));
        int numberOfCalls = 0;
        int cycleCount=0;

        while (LocalDateTime.now().isBefore(finalTime)) {
            outer:
            if (dashboardPage.isIncomingCallAvailable()) {
                waitFor(1);
                dashboardPage.acceptCallButton.click();

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
