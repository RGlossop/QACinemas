package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.Homepage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ContactUsTests {
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
    public void testBContactUs() throws InterruptedException {
        website.bNavContactUs(driver);
        assertTrue(website.getTitle().getText().contains("Contact"));
    }
    @Test
    public void testSendEmail() throws InterruptedException {
        website.navContactUs();
        Thread.sleep(1000);
        website.contactUs.writeEmail("emailSender@Gmail.com", "I like your site", "Hey this site is great!");
        Thread.sleep(1000);
        assertTrue(website.getFirstLine().getText().contains("QACinemas"));
    }
    @Test
    public void testSendEmailFail() {
        website.navContactUs();
        website.contactUs.writeEmail("This is not an email address", "I like your site", "Hey this site is great!");
        assertTrue(website.getTitle().getText().contains("Contact"));
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}
