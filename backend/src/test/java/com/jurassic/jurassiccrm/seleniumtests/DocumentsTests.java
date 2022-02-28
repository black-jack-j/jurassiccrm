package com.jurassic.jurassiccrm.seleniumtests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Random;

import static java.lang.Thread.sleep;

@Disabled
public class DocumentsTests {

    private static WebDriver chromedriver;
    private static WebDriver firefoxdriver;

    private final String randString1 = java.util.UUID.randomUUID().toString().substring(0, 7);
    private final String randString2 = java.util.UUID.randomUUID().toString().substring(0, 7);

    private static void loginJCRM(WebDriver driver) {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@value='Sign In']")).click();
    }

    private static void createAviaryPassport(WebDriver driver, String documentName) throws InterruptedException {
        driver.findElement(By.linkText("Document")).click();
        driver.findElement(By.cssSelector(".plus")).click();
        sleep(1000);
        driver.findElement(By.xpath("//td[text()='Aviary Passport']")).click();
        driver.findElement(By.xpath("//button[text()='Select']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Document Name']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Document Name']")).sendKeys(documentName);
        driver.findElement(By.xpath("//div[text()='Aviary Type']")).click();
        driver.findElement(By.xpath("//span[text()='Open aviary']")).click();
        sleep(100);
        driver.findElement(By.xpath("//input[@placeholder='Square']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Square']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Built Date (yyyy-MM-dd)']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Built Date (yyyy-MM-dd)']")).sendKeys("2000-01-01");
        driver.findElement(By.xpath("//input[@placeholder='Revision period (days)']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Revision period (days)']")).sendKeys("202");
        driver.findElement(By.xpath("//input[@placeholder='Aviary Code']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Aviary Code']")).sendKeys(String.valueOf(new Random().nextInt(88888)));
        driver.findElement(By.xpath("//input[@placeholder='Status']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Status']")).sendKeys("test");
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).click();
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).sendKeys("test");
        driver.findElement(By.xpath("//button[text()='Create']")).click();
        sleep(1000);
        driver.findElement(By.cssSelector(".refresh")).click();
        sleep(1000);
        String xpathAssert = "//h3[text()='DOC']".replace("DOC", documentName);
        assert driver.findElements(By.xpath(xpathAssert)).size() == 1;
    }

    private static void createDinosaurPassport(WebDriver driver, String documentName) throws InterruptedException {
        driver.findElement(By.linkText("Document")).click();
        driver.findElement(By.cssSelector(".plus")).click();
        sleep(1000);
        driver.findElement(By.xpath("//td[text()='Dinosaur Passport']")).click();
        driver.findElement(By.xpath("//button[text()='Select']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Document Name']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Document Name']")).sendKeys(documentName);
        driver.findElement(By.xpath("//div[text()='Dinosaur Type']")).click();
        driver.findElement(By.xpath("//span[text()='Tyrannosaurus']")).click();
        sleep(100);
        driver.findElement(By.xpath("//input[@placeholder='Dinosaur Name']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Dinosaur Name']")).sendKeys(documentName);
        driver.findElement(By.xpath("//input[@placeholder='Weight']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Weight']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Height']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Height']")).sendKeys("1");
        driver.findElement(By.xpath("//input[@placeholder='Incubation Date (yyyy-MM-dd)']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Incubation Date (yyyy-MM-dd)']")).sendKeys("2000-01-01");
        driver.findElement(By.xpath("//div[text()='Status']")).click();
        driver.findElement(By.xpath("//span[text()='BORN']")).click();
        sleep(100);
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).click();
        driver.findElement(By.xpath("//textarea[@placeholder='Description']")).sendKeys("test");
        driver.findElement(By.xpath("//button[text()='Create']")).click();
        sleep(1000);
        driver.findElement(By.cssSelector(".refresh")).click();
        sleep(1000);
        String xpathAssert = "//h3[text()='DOC']".replace("DOC", documentName);
        assert driver.findElements(By.xpath(xpathAssert)).size() == 1;
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
    public void createNewAviaryPassport() throws InterruptedException {
        createAviaryPassport(chromedriver, randString1);
        createAviaryPassport(firefoxdriver, randString2);
    }

    @Test
    @Order(2)
    public void createNewDinosaurPassport() throws InterruptedException {
        createDinosaurPassport(chromedriver, randString1);
        createDinosaurPassport(firefoxdriver, randString2);
    }

    @AfterAll
    public static void closeBrowser() {
        chromedriver.close();
        firefoxdriver.close();
    }
}
