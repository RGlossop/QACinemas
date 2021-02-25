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

public class ScreensTests {
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
    public void testScreensSelector() {
        website.navOurScreens();
        website.screens.selectPremium();
        assertTrue(website.screens.checkType().getText().contains("Premium"));
    }
    @After
    public void cleanUp() {
        driver.close();
    }
}
