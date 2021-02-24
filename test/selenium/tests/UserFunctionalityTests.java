package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import selenium.pages.Homepage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class UserFunctionalityTests {
    private static WebDriver driver;
    private static Homepage website;

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Ryan/Desktop/QACinemas/test/resources/drivers/chrome/chromedriver.exe");
    }
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        website = PageFactory.initElements(driver, Homepage.class);
    }
    @Test
    public void testSignUp() throws InterruptedException {
        website.navSignUp();
        website.signUp.signUp("Ryan", "Glossop", "06061993", "RyanG123", "ff@fs.com", "password123");
        assertTrue(website.getTitle().getText().contains("QACinemas"));
    }
    @Test
    public void testLogin() throws InterruptedException {
        website.navLogin();
        Thread.sleep(2000);
        website.login.adminLogin();
        Thread.sleep(2000);
        assertTrue(website.getNavLogin().getText().contains("Logout"));
    }
    @Test
    public void testOrder() throws InterruptedException {
        website.navLogin();
        Thread.sleep(2000);
        website.login.adminLogin();
        website.navNewReleases();
        Thread.sleep(2000);
        website.newReleases.clickFilm();
        WebElement myElement = driver.findElement(By.xpath("//*[@id=\"send2booking\"]"));
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].scrollIntoView()", myElement);
        Thread.sleep(2000);
        website.film.goToBookings();
        website.bookingPage.bookTicket("2", "1", "2");
        website.payment.acceptOrder();
        WebElement payPal = driver.findElement(By.xpath("//*[@id=\"headerText\"]"));
        assertTrue(payPal.getText().contains("PayPal"));
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}
