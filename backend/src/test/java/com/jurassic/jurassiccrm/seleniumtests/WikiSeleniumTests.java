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

    public static void loginJCRM(WebDriver driver) {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@value='Sign In']")).click();
    }

    @BeforeAll
    public static void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        chromedriver = new ChromeDriver();
        firefoxdriver = new FirefoxDriver();
        loginJCRM(chromedriver);
        loginJCRM(firefoxdriver);
    }

    @Test
    @Order(1)
    public void createNewWiki() throws InterruptedException {
        chromedriver.get("http://localhost:8080/wiki/home/");
        chromedriver.findElement(By.linkText("Создать новую страницу Вики")).click();
        chromedriver.findElement(By.id("title")).click();
        chromedriver.findElement(By.id("title")).sendKeys("Test");
        chromedriver.findElement(By.cssSelector("textarea")).click();
        chromedriver.findElement(By.cssSelector("textarea")).sendKeys("Test");
        chromedriver.findElement(By.id("submit")).click();
        sleep(1000);
        assert chromedriver.findElement(By.xpath("//*[contains(text(), 'Test')]")).isDisplayed();

        firefoxdriver.get("http://localhost:8080/wiki/home/");
        firefoxdriver.findElement(By.linkText("Создать новую страницу Вики")).click();
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
        chromedriver.get("http://localhost:8080/wiki/admin");
        chromedriver.findElement(By.xpath("//a[contains(@href, '/wiki/edit?pageName=Test')]")).click();
        chromedriver.findElement(By.cssSelector("textarea")).click();
        chromedriver.findElement(By.cssSelector("textarea")).sendKeys("TestTestTest");
        chromedriver.findElement(By.xpath("//input[@value='Применить изменения']")).click();
        sleep(1000);
        chromedriver.get("http://localhost:8080/wiki/home");
        chromedriver.findElement(By.linkText("Test")).click();
        assert chromedriver.findElement(By.xpath("//p")).getText().equals("TestTestTestTest");

        firefoxdriver.get("http://localhost:8080/wiki/admin");
        firefoxdriver.findElement(By.xpath("//a[contains(@href, '/wiki/edit?pageName=Test1')]")).click();
        firefoxdriver.findElement(By.cssSelector("textarea")).click();
        firefoxdriver.findElement(By.cssSelector("textarea")).sendKeys("TestTestTest");
        firefoxdriver.findElement(By.xpath("//input[@value='Применить изменения']")).click();
        firefoxdriver.get("http://localhost:8080/wiki/home");
        sleep(1000);
        firefoxdriver.findElement(By.linkText("Test1")).click();
        assert firefoxdriver.findElement(By.xpath("//p")).getText().equals("TestTestTestTest");
    }

    @Test
    @Order(3)
    public void deleteWiki() throws InterruptedException {
        chromedriver.get("http://localhost:8080/wiki/admin");
        chromedriver.findElement(By.xpath("//a[@id='Test']")).click();
        Alert javascriptAlert = chromedriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        javascriptAlert = chromedriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        chromedriver.get("http://localhost:8080/wiki/home");
        sleep(1000);
        assert chromedriver.findElements(By.linkText("Test")).size() == 0;

        firefoxdriver.get("http://localhost:8080/wiki/admin");
        firefoxdriver.findElement(By.xpath("//a[@id='Test1']")).click();
        javascriptAlert = firefoxdriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        javascriptAlert = firefoxdriver.switchTo().alert();
        javascriptAlert.accept();
        sleep(1000);
        firefoxdriver.get("http://localhost:8080/wiki/home");
        sleep(1000);
        assert firefoxdriver.findElements(By.linkText("Test1")).size() == 0;
    }

    @AfterAll
    public static void closeBrowser() {
        chromedriver.close();
        firefoxdriver.close();
    }
}
