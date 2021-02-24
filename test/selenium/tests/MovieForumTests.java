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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        website = PageFactory.initElements(driver, Homepage.class);
    }
    @Test
    public void testAddComment() throws InterruptedException {
        website.navLogin();
        Thread.sleep(2000);
        website.login.adminLogin();
        website.navMovieForum();
        Thread.sleep(2000);
        website.movieForum.selectFilm();
        website.movieForum.addComment("This film was great, RIP heath ledger");
        Thread.sleep(2000);
        website.movieForum.selectFilm();
        Thread.sleep(2000);
        assertTrue(website.movieForum.getCommentPoster().getText().contains("Admin123"));
    }
    @Test
    public void testAddSwears() throws InterruptedException {
        website.navLogin();
        Thread.sleep(2000);
        website.login.adminLogin();
        website.navMovieForum();
        Thread.sleep(2000);
        website.movieForum.selectFilm();
        Thread.sleep(2000);
        website.movieForum.addComment("this site fucking sucks");
        assertTrue(website.getTitle().getText().contains("QACinemas"));
    }
    @After
    public void cleanUp() {
        driver.close();
    }
}
