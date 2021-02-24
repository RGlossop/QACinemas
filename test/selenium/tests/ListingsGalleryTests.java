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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListingsGalleryTests {
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
    public void testListingsAreShown() {
        website.navAllFilms();
        assertTrue(website.listingsGallery.getListing1().getAttribute("class").contains("item"));
    }

    @Test
    public void testMovieClick() throws InterruptedException {
        website.navAllFilms();
        Thread.sleep(2000);
        website.listingsGallery.clickFilm();
        assertFalse(website.film.getDescription().getText().isEmpty());
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}
