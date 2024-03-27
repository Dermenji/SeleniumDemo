package dev.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


public class ActionsAndSelectDemo {
    WebDriver driver;

    @BeforeMethod
    public void before() {
        driver = new ChromeDriver();

    }

    @Test
    public void sliderTest() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement slider = driver.findElement(By.cssSelector(".form-range"));
        Actions moveSlider = new Actions(driver);
        moveSlider.clickAndHold(slider).moveByOffset(40, 0).perform();
    }

    @Test
    public void dragAndDrop() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));
        Actions drag = new Actions(driver);

        Thread.sleep(2000);

        drag.clickAndHold(source).moveToElement(target).release().perform();

    }

    @Test
    public void sendKeysTest() {
        driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(By.id("target"));

        input.sendKeys(Keys.ESCAPE);

        WebElement message = driver.findElement(By.id("result"));
        assertEquals(message.getText(), "You entered: ESCAPE");

    }


    @Test
    public void dropDownTest() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement select = driver.findElement(By.id("dropdown"));

        Select dropDown = new Select(select);
        dropDown.selectByVisibleText("Option 1");
        dropDown.selectByValue("2");

    }


    @Test
    public void tableExtraction() {
        driver.get("https://the-internet.herokuapp.com/tables");

        List<WebElement> emails = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[3]"));
        for (WebElement email: emails) {
            System.out.println(email.getText());
        }
        driver.getCurrentUrl();

    }


    @Test
    public void paginationTest() {
       // WebDriver driver = new ChromeDriver();
        driver.get("https://pagination.js.org/");
        WebElement demo1 = driver.findElement(By.id("demo1"));
        List<WebElement> items = demo1.findElements(By.cssSelector(".data-container ul li"));
        List<WebElement> pagination = demo1.findElements(By.cssSelector(".paginationjs-pages ul li"));

        pagination.get(2).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.stalenessOf(items.get(0)));

        items = demo1.findElements(By.cssSelector(".data-container ul li"));
        assertEquals("11", items.get(0).getText());


    }

    @Test
    public void waitVisibility() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement helloWorldText = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));

        assertEquals("Hello World!", helloWorldText.getText());

    }


    @Test
    public void addRemoveTest() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement checkBox = driver.findElement(By.cssSelector("[type=checkbox]"));
        checkBox.click();

        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        removeButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

        assertEquals("It's gone!", message.getText());
    }


    @Test
    public void acceptAlertTest() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElement(By.xpath("//button[contains(text(), 'Click for JS Alert')]"))
                .click();

       // driver.switchTo().alert().accept();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        WebElement result = driver.findElement(By.id("result"));
        assertEquals("You successfully clicked an alert", result.getText());
    }

    @Test
    public void newTabTest() {
        driver.get("https://the-internet.herokuapp.com/windows");
        WebElement link = driver.findElement(By.linkText("Click Here"));
        link.click();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement header = driver.findElement(By.tagName("h3"));
        String headerText= header.getText();
        assertEquals("New Window", headerText);

        driver.close();
        driver.switchTo().window(tabs.get(0));




    }

    @Test
    public void nestedFramesTest(){
        driver.get("https://the-internet.herokuapp.com/frames");

        driver.findElement(By.linkText("Nested Frames")).click();

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        WebElement leftElemnt = driver.findElement(By.tagName("body"));
        leftElemnt.getText();

        driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }

    @Test
    public void editorTest(){
        driver.get("https://the-internet.herokuapp.com/frames");
        driver.findElement(By.linkText("iFrame")).click();

        driver.switchTo().frame("mce_0_ifr");

        WebElement editable = driver.findElement(By.id("tinymce"));
        editable.clear();
        editable.sendKeys("Testing iFrame content editing.");

        driver.switchTo().defaultContent();
    }


    @AfterMethod
    public void after() {
       // driver.quit();
    }
}
