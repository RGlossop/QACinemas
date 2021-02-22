package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.Homepage;

import static org.junit.Assert.assertTrue;

public class ClassificationsTests {
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
    public void testClassifications() {
        website.navClassifications();
        assertTrue(website.classifications.getFirstCardTitle().getText().contains("U"));
    }
    @Test
    public void testExternalLink() throws InterruptedException {
        website.navClassifications();
        website.classifications.clickFirstCard();
        Thread.sleep(1000);
        WebElement externalTitle = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div[2]/div[1]/div/div/h2"));
        assertTrue(externalTitle.getText().contains("U"));
    }
    @After
    public void cleanUp() {
        driver.close();
    }
}
