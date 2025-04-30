package ru.aleksey.NauJava;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    WebDriver webDriver;
    WebDriverWait wait;

    @BeforeAll
    static void setUpClass() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        webDriver = new EdgeDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    void successfulLogin() {
        webDriver.get("http://localhost:8082/login");
        WebElement elementLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        elementLogin.sendKeys("qwe");

        WebElement elementPassword = webDriver.findElement(By.id("password"));
        elementPassword.sendKeys("qwe");

        WebElement buttonLogin = webDriver.findElement(By.className("btn"));
        buttonLogin.click();

        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-brand")));
        Assertions.assertEquals("Школьный дневник", productTitle.getText());
    }

    @Test
    void successfulLogout() {
        webDriver.get("http://localhost:8082/login");
        WebElement elementLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        elementLogin.sendKeys("qwe");
        WebElement elementPassword = webDriver.findElement(By.id("password"));
        elementPassword.sendKeys("qwe");
        WebElement buttonLogin = webDriver.findElement(By.className("btn"));
        buttonLogin.click();
        WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-brand")));
        Assertions.assertEquals("Школьный дневник", productTitle.getText());

        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("navbar-toggler-icon")));
        menu.click();
        WebElement buttonLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-outline-danger")));
        buttonLogout.click();

        WebElement loginTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-4")));
        Assertions.assertEquals("Авторизация", loginTitle.getText());
    }
}
