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

public class MovieForumTests {
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
    public void testAddComment() throws InterruptedException {
        website.navMovieForum();
        Thread.sleep(1000);
        website.movieForum.addComment("Ryan", "Lets go my homie");
        Thread.sleep(2000);
        assertTrue((website.movieForum.getComments().getText().contains("Ryan")));
    }
    @Test
    public void testAddSwears() throws InterruptedException {
        website.navMovieForum();
        Thread.sleep(1000);
        website.movieForum.addComment("Ryan", "this site fucking sucks");
        Thread.sleep(2000);
        assertTrue(website.getTitle().getText().contains("QACinemas"));
    }
    @After
    public void cleanUp() {
        driver.close();
    }
}
