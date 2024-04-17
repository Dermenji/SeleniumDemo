package dev.selenium.steps;

import dev.selenium.driver.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;

public class TestHook {
  //  public WebDriver driver;
    private String url;
    private int implicitWait;
    private String browser;


    @Before
    public void setUp() {
       setupBrowserDriver();
       loadUrl();
    }

    private void setupBrowserDriver() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")){
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (browser) {
            case "chrome":
                DriverFactory.setChromeDriver(implicitWait);
                break;
            case "firefox":
                DriverFactory.setFirefoxDriver(implicitWait);
                break;
            default:
                throw new IllegalStateException("Unsupported browser type");
        }
    }

    private void loadUrl() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
    }

    @After
    public void tearDown(Scenario scenario){
        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed()) {
          TakesScreenshot ts = (TakesScreenshot) driver;
          byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
          scenario.attach(screenshot, "image/png",
                  "Screenshot_" + new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date()));
        }

        DriverFactory.quitDriver();
    }
}
