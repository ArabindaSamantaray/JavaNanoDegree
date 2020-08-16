package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSignupAndLogin {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll(){
        if(driver!=null){
            driver.quit();
        }
    }

    public void goToPath(String path){
        driver.get("http://localhost:"+port+"/"+path);
    }

    @Test
    @Order(1)
    public void testGoingToHomePageWithoutLogin() throws InterruptedException {
        this.goToPath("home");
        WebElement login = driver.findElement(By.id("LoginHeader"));
        Assert.assertEquals(login.getText(), "Login");
        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    public void testAttemptToLoginWithoutSignup() throws InterruptedException {
        this.goToPath("home");
        driver.findElement(By.id("inputUsername")).sendKeys("arabinda");
        driver.findElement(By.id("inputPassword")).sendKeys("SURANJANARAY");
        driver.findElement(By.id("LoginButton")).click();
        WebElement errorMessage = driver.findElement(By.id("errorMessage"));
        Assert.assertEquals(errorMessage.getText(),"Invalid username or password");
        Thread.sleep(3000);
    }

    @Test
    @Order(3)
    public void testAttemptToSignup() throws InterruptedException {
        this.goToPath("signup");
        driver.findElement(By.id("inputFirstName")).sendKeys("Arabinda");
        driver.findElement(By.id("inputLastName")).sendKeys("Samantaray");
        driver.findElement(By.id("inputUsername")).sendKeys("samantarayarabinda");
        driver.findElement(By.id("inputPassword")).sendKeys("SURANJANARAY");
        driver.findElement(By.id("signUp")).click();

        WebElement successMessage = driver.findElement(By.id("successMessage"));
        Assert.assertEquals(successMessage.getText(),"You successfully signed up! Please continue to the login page.");
        Thread.sleep(3000);
    }

    @Test
    @Order(4)
    public void testSuccessfulLogin() throws InterruptedException {
        this.goToPath("home");
        driver.findElement(By.id("inputUsername")).sendKeys("samantarayarabinda");
        driver.findElement(By.id("inputPassword")).sendKeys("SURANJANARAY");
        driver.findElement(By.id("LoginButton")).click();
        Assert.assertEquals(driver.findElement(By.id("nav-files-tab")).getText(), "Files");
        Thread.sleep(3000);
    }

    @Test
    @Order(5)
    public void testNoteCreation() throws InterruptedException {
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("createNotesButton"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title"))).sendKeys("Title");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-description"))).sendKeys("Description");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveNoteChanges"))).click();
        this.goToPath("home");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab"))).click();
        String title = wait.until(ExpectedConditions.elementToBeClickable(By.id("noteTitle"))).getText();
        Assert.assertEquals(title,"Title");
        Thread.sleep(3000);
    }

    @Test
    @Order(6)
    public void testNoteEditing() throws InterruptedException{
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("editNotes"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title"))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title"))).sendKeys(" changed the title");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-description"))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-description"))).sendKeys("changed the description");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveNoteChanges"))).click();
        this.goToPath("home");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab"))).click();
        String title = wait.until(ExpectedConditions.elementToBeClickable(By.id("noteTitle"))).getText();
        Assert.assertEquals(title,"changed the title");
        Thread.sleep(3000);
    }

    @Test
    @Order(7)
    public void testNoteDeletion() throws InterruptedException{
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteNote"))).click();
        Thread.sleep(1000);
        String text = driver.findElement(By.id("successMessage")).getText();
        Assert.assertEquals(text, "The note was deleted successfully.");
        Thread.sleep(3000);
    }

    @Test
    @Order(8)
    public void createCredentials() throws InterruptedException {
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addCredentials"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).sendKeys("www.google.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username"))).sendKeys("Arabinda");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password"))).sendKeys("google");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveCredentialChanges"))).click();
        Thread.sleep(1000);
        String text = driver.findElement(By.id("successMessage")).getText();
        Assert.assertEquals(text, "The credentials were correctly stored in the database.");
        this.goToPath("home");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab"))).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("url")), "www.google.com"));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("credentialUserName")), "Arabinda"));
        String password = driver.findElement(By.id("credentialPassword")).getText();
        Assert.assertNotEquals(password, "google");
        Thread.sleep(3000);
    }

    @Test
    @Order(9)
    public void editCredentials() throws InterruptedException {
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("editCredential"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).sendKeys("www.facebook.com");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username"))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username"))).sendKeys("sachin");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password"))).clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password"))).sendKeys("facebook");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("saveCredentialChanges"))).click();
        this.goToPath("home");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab"))).click();
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("url")), "www.facebook.com"));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("credentialUserName")), "sachin"));
        Thread.sleep(3000);
    }

    @Test
    @Order(10)
    public void deleteCredentials() throws InterruptedException {
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("deleteCredential"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("successMessage")),
            "The credentials were correctly deleted from the database."));
        Thread.sleep(3000);

    }

    @Test
    @Order(11)
    public void testLogout() throws InterruptedException {
        this.goToPath("home");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("logoutButton")))).click();
        this.goToPath("home");
        WebElement login = driver.findElement(By.id("LoginHeader"));
        Assert.assertEquals(login.getText(), "Login");
        Thread.sleep(3000);
    }

}
