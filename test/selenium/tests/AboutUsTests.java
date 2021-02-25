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

public class AboutUsTests {
    private static WebDriver driver;
    private static Homepage website;

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "test/resources/drivers/chrome/chromedriver.exe");
    }
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        website = PageFactory.initElements(driver, Homepage.class);
    }
    @Test
    public void testAboutUs() {
        website.navAboutUs();
        assertTrue(website.aboutUs.getPageTitle().getText().contains("Team"));
    }
    @Test
    public void testBAboutUs() throws InterruptedException {
        website.bNavAboutUs(driver);
        assertTrue(website.aboutUs.getPageTitle().getText().contains("Team"));
    }
    @Test
    public void testContactLink() throws InterruptedException {
        website.navAboutUs();
        Thread.sleep(2000);
        website.aboutUs.contactUsLink();
        assertTrue(website.getTitle().getText().contains("Contact"));
    }
    @Test
    public void testExternalLink() throws InterruptedException {
        website.navAboutUs();
        WebElement myElement = driver.findElement(By.xpath("//*[@id=\"page\"]/div[3]/div/div[3]/div[1]/p[3]/a"));
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].scrollIntoView()", myElement);
        Thread.sleep(2000);
        website.aboutUs.clickExternalLink();
        WebElement externalTitle = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/main/div[2]/div/div[1]/section/h2"));
        assertTrue(externalTitle.getText().contains("SCRUM"));
    }
    @After
    public void cleanUp() {
        driver.close();
    }
}
