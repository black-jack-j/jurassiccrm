package com.jurassic.jurassiccrm.seleniumtests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.Thread.sleep;

@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikiTests {

    private static WebDriver chromedriver;
    private static WebDriver firefoxdriver;

    private static void loginJCRM(WebDriver driver) {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@value='Sign In']")).click();
    }

    private static void createWiki(WebDriver driver, String pageName) throws InterruptedException {
        driver.get("http://localhost:8080/wiki/home/");
        driver.findElement(By.linkText("Создать новую страницу Вики")).click();
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).sendKeys(pageName);
        driver.findElement(By.cssSelector("textarea")).click();
        driver.findElement(By.cssSelector("textarea")).sendKeys("Test");
        driver.findElement(By.id("submit")).click();
        sleep(1000);
        String xpathAssert = "//*[contains(text(), 'pageName')]".replace("pageName", pageName);
        assert driver.findElement(By.xpath(xpathAssert)).isDisplayed();
    }

    private static void editWiki(WebDriver driver, String pageName) throws InterruptedException {
        driver.get("http://localhost:8080/wiki/admin");
        String xpath = "//a[contains(@href, '/wiki/edit?pageName=Test')]".replace("pageName=Test", "pageName=" + pageName);
        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.cssSelector("textarea")).click();
        driver.findElement(By.cssSelector("textarea")).sendKeys("TestTestTest");
        driver.findElement(By.xpath("//input[@value='Применить изменения']")).click();
        sleep(1000);
        driver.get("http://localhost:8080/wiki/home");
        driver.findElement(By.linkText(pageName)).click();
        assert driver.findElement(By.xpath("//p")).getText().equals("TestTestTestTest");
    }

    private static void deleteWiki(WebDriver driver, String pageName) throws InterruptedException {
        driver.get("http://localhost:8080/wiki/admin");
        String xpath = "//a[@id='Test']".replace("Test", pageName);
        driver.findElement(By.xpath(xpath)).click();
        Alert javascriptAlert = driver.switchTo().alert();
        javascriptAlert.accept();
        sleep(2000);
        javascriptAlert = driver.switchTo().alert();
        javascriptAlert.accept();
        sleep(2000);
        driver.get("http://localhost:8080/wiki/home");
        sleep(2000);
        assert driver.findElements(By.linkText(pageName)).size() == 0;
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
        createWiki(chromedriver, "Test");
        createWiki(firefoxdriver, "Test1");
    }

    @Test
    @Order(2)
    public void editWiki() throws InterruptedException {
        editWiki(chromedriver, "Test");
        editWiki(firefoxdriver, "Test1");
    }

    @Test
    @Order(3)
    public void deleteWiki() throws InterruptedException {
        deleteWiki(chromedriver, "Test");
        deleteWiki(firefoxdriver, "Test1");
    }

    @AfterAll
    public static void closeBrowser() {
        chromedriver.close();
        firefoxdriver.close();
    }
}
