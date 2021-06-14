package miCollab.utilities;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class WindowsDriverSetup {

    public static WindowsDriver windowsDriver=null;

    public static void setUp() throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("app", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        desiredCapabilities.setCapability("platformName", "Windows");
        desiredCapabilities.setCapability("deviceName", "WindowsPC");
        try {
            windowsDriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public static void tearDown(){
        windowsDriver.quit();
    }

}
