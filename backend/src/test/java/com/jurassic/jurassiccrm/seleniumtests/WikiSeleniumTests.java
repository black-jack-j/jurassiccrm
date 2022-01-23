package com.jurassic.jurassiccrm.seleniumtests;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.Thread.sleep;

@Disabled
public class WikiSeleniumTests {

    public static WebDriver chromedriver;
    public static WebDriver firefoxdriver;

    @BeforeAll
    public static void openBrowser() {
        ChromeDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();
        chromedriver = new ChromeDriver();
        firefoxdriver = new FirefoxDriver();
    }

    @Test
    @Order(1)
    public void createNewWiki() throws InterruptedException {
        chromedriver.get("http://localhost:3000/");
        chromedriver.findElement(By.linkText("Create New Page")).click();
        chromedriver.findElement(By.id("title")).click();
        chromedriver.findElement(By.id("title")).sendKeys("Test");
        chromedriver.findElement(By.cssSelector("textarea")).click();
        chromedriver.findElement(By.cssSelector("textarea")).sendKeys("Test");
        chromedriver.findElement(By.id("submit")).click();
        sleep(1000);
        assert chromedriver.findElement(By.xpath("//*[contains(text(), 'Test')]")).isDisplayed();

        firefoxdriver.get("http://localhost:3000/");
        firefoxdriver.findElement(By.linkText("Create New Page")).click();
        firefoxdriver.findElement(By.id("title")).click();
        firefoxdriver.findElement(By.id("title")).sendKeys("Test1");
        firefoxdriver.findElement(By.cssSelector("textarea")).click();
        firefoxdriver.findElement(By.cssSelector("textarea")).sendKeys("Test");
        firefoxdriver.findElement(By.id("submit")).click();
        sleep(1000);
        assert firefoxdriver.findElement(By.xpath("//*[contains(text(), 'Test1')]")).isDisplayed();
    }

    @Test
    @Order(2)
    public void editWiki() throws InterruptedException {
        chromedriver.get("http://localhost:3000/wiki/admin");
        chromedriver.findElement(By.xpath("(//button[@value='Test'])[last()-1]")).click();
        chromedriver.findElement(By.cssSelector("textarea")).click();
        chromedriver.findElement(By.cssSelector("textarea")).sendKeys("TestTestTest");
        chromedriver.findElement(By.id("submit")).click();
        sleep(1000);
        chromedriver.get("http://localhost:3000/wiki/home");
        chromedriver.findElement(By.xpath("//*[contains(text(), 'Test')]")).click();
        assert chromedriver.findElement(By.xpath("//pre")).getText().equals("TestTestTestTest");

        firefoxdriver.get("http://localhost:3000/wiki/admin");
        firefoxdriver.findElement(By.xpath("(//button[@value='Test1'])[last()-1]")).click();
        firefoxdriver.findElement(By.cssSelector("textarea")).click();
        firefoxdriver.findElement(By.cssSelector("textarea")).sendKeys("TestTestTest");
        firefoxdriver.findElement(By.id("submit")).click();
        firefoxdriver.get("http://localhost:3000/wiki/home");
        sleep(1000);
        firefoxdriver.findElement(By.xpath("//*[contains(text(), 'Test1')]")).click();
        assert firefoxdriver.findElement(By.xpath("//pre")).getText().equals("TestTestTestTest");
    }

    @Test
    @Order(3)
    public void deleteWiki() throws InterruptedException {
        chromedriver.get("http://localhost:3000/wiki/admin");
        chromedriver.findElement(By.xpath("(//button[@value='Test1'])[last()]")).click();
        Alert javascriptAlert = chromedriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        javascriptAlert = chromedriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        chromedriver.get("http://localhost:3000/wiki/home");
        sleep(1000);
        assert chromedriver.findElements(By.xpath("//*[contains(text(), 'Test1')]")).size() == 0;

        firefoxdriver.get("http://localhost:3000/wiki/admin");
        firefoxdriver.findElement(By.xpath("(//button[@value='Test'])[last()]")).click();
        javascriptAlert = firefoxdriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        javascriptAlert = firefoxdriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        firefoxdriver.get("http://localhost:3000/wiki/home");
        sleep(1000);
        assert firefoxdriver.findElements(By.xpath("//*[contains(text(), 'Test')]")).size() == 0;
    }

    @AfterAll
    public static void closeBrowser() {
        chromedriver.close();
        firefoxdriver.close();
    }
}
