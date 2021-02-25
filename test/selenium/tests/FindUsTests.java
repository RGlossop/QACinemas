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

public class FindUsTests {
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
    public void testFindUsNavigation() {
        website.navFindUs();
        assertTrue((website.getTitle().getText().contains("Find")));
    }
    @Test
    public void testBFindUsNavigation() throws InterruptedException {
        website.bFindUs(driver);
        assertTrue((website.getTitle().getText().contains("Find")));
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}
