package iOS;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AlertTest {

        public IOSDriver driver;

        @BeforeTest
        public void setup() throws MalformedURLException {

            String appiumServerUrl = "http://127.0.0.1:4723";

            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("platformName", "IOS");
            dc.setCapability("appium:automationName", "XCUITest");
            dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/UIKitCatalog.app");
            dc.setCapability("appium:deviceName", "iPhone 15 Pro");
            //test capabilities
            dc.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
            driver = new IOSDriver(new URL(appiumServerUrl), dc);
        }

        @Test
        public void simpleAlert() {
            driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
            driver.findElement(AppiumBy.accessibilityId("Simple")).click();
            Alert alert = driver.switchTo().alert();

            Assert.assertEquals(alert.getText(), "A Short Title Is Best\n" +
                    "A message should be a short, complete sentence.");

            alert.accept();

        }

        @Test
        public void okCancelAlert(){
            driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
            driver.findElement(AppiumBy.accessibilityId("Okay / Cancel")).click();
            Alert alert = driver.switchTo().alert();

            Assert.assertEquals(alert.getText(), "A Short Title Is Best\n" +
                    "A message should be a short, complete sentence.");

            alert.dismiss();

        }

        @Test
        public void textEntryAlert(){
            driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
            driver.findElement(AppiumBy.accessibilityId("Text Entry")).click();
            driver.switchTo().alert().sendKeys("Text LucQAs");
            driver.switchTo().alert().accept();
        }

        @Test
        public void otherAlert(){
            driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
            driver.findElement(AppiumBy.accessibilityId("Other")).click();
            String msg = driver.switchTo().alert().getText();

            Assert.assertTrue(msg.contains(("Short Title")));
            driver.switchTo().alert().dismiss();
        }

        @AfterTest
        public void close() {
            driver.quit();
        }

}
