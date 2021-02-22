package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.Homepage;

import static org.junit.Assert.assertTrue;

public class ContactUsTests {
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
        website = PageFactory.initElements(driver, Homepage.class);
    }
    @Test
    public void testSendEmail() throws InterruptedException {
        website.navContactUs();
        Thread.sleep(1000);
        website.contactUs.writeEmail("emailSender@Gmail.com", "I like your site", "Hey this site is great!");
        Thread.sleep(2000);
        assertTrue(website.getTitle().getText().contains("QACinemas"));
    }
    @Test
    public void testSendEmailFail() throws InterruptedException {
        website.navContactUs();
        Thread.sleep(1000);
        website.contactUs.writeEmail("This is not an email address", "I like your site", "Hey this site is great!");
        Thread.sleep(2000);
        assertTrue(website.getTitle().getText().contains("Contact"));
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}
