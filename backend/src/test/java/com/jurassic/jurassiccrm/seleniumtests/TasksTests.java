package com.jurassic.jurassiccrm.seleniumtests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.Thread.sleep;

@Disabled
public class TasksTests {

    private static WebDriver chromedriver;
    private static WebDriver firefoxdriver;

    private static final String randString1 = java.util.UUID.randomUUID().toString().substring(0, 9);
    private static final String randString2 = java.util.UUID.randomUUID().toString().substring(0, 9);

    private static void loginJCRM(WebDriver driver) {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@value='Sign In']")).click();
    }

    private static void createTask(WebDriver driver, String taskName) throws InterruptedException {
        driver.findElement(By.linkText("Task")).click();
        driver.findElement(By.cssSelector(".plus")).click();
        sleep(1000);
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).sendKeys(taskName);
        driver.findElement(By.xpath("//input[@placeholder='Assignee']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Assignee']")).sendKeys("admin");
        sleep(100);
        driver.findElement(By.xpath("//div[@class='result']")).click();
        driver.findElement(By.xpath("//div[text()='Priority']")).click();
        driver.findElement(By.xpath("//span[text()='normal']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Goal']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Goal']")).sendKeys("test goal");
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).click();
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).sendKeys("test desc");
        driver.findElement(By.xpath("//button[text()='Create']")).click();
        sleep(1000);
        driver.findElement(By.cssSelector(".refresh")).click();
        sleep(100);
        String xpathAssert = "//h3[text()='taskName']".replace("taskName", taskName);
        assert driver.findElements(By.xpath(xpathAssert)).size() == 1;
    }

    private static void changeStatus(WebDriver driver, String taskName) throws InterruptedException {
        driver.findElement(By.linkText("Task")).click();
        String xpathAssert = "//h3[text()='taskName']".replace("taskName", taskName);
        sleep(1000);
        driver.findElement(By.cssSelector(".refresh")).click();
        sleep(100);
        driver.findElement(By.xpath(xpathAssert)).click();
        sleep(100);
        driver.findElement(By.xpath("//div[text()='Move to']")).click();
        driver.findElement(By.xpath("//div[text()='IN_PROGRESS']")).click();
        assert driver.findElements(By.xpath("//div[text()='IN_PROGRESS']")).size() == 1;
        sleep(100);
        driver.findElement(By.xpath("//div[text()='Move to']")).click();
        driver.findElement(By.xpath("//div[text()='RESOLVED']")).click();
        assert driver.findElements(By.xpath("//div[text()='RESOLVED']")).size() == 1;
        sleep(100);
        driver.findElement(By.xpath("//div[text()='Move to']")).click();
        driver.findElement(By.xpath("//div[text()='CLOSED']")).click();
        assert driver.findElements(By.xpath("//div[text()='CLOSED']")).size() == 1;
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
    public void createNewTask() throws InterruptedException {
        createTask(chromedriver, randString1);
        createTask(firefoxdriver, randString2);
    }

    @Test
    @Order(2)
    public void changeTaskStatus() throws InterruptedException {
        changeStatus(chromedriver, randString1);
        changeStatus(firefoxdriver, randString2);
    }

    @AfterAll
    public static void closeBrowser() {
        chromedriver.close();
        firefoxdriver.close();
    }
}
