package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.Homepage;

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
        website = PageFactory.initElements(driver, Homepage.class);
    }
    @Test
    public void testListingsAreShown() throws InterruptedException {
        website.navAllFilms();
        Thread.sleep(1000);
        assertTrue(website.listingsGallery.getListing1().getAttribute("class").contains("content"));
    }

    @Test
    public void testMovieClick() throws InterruptedException {
        website.navAllFilms();
        Thread.sleep(2000);
        website.listingsGallery.clickFilm();
        Thread.sleep(2000);
        assertFalse(website.film.getDescription().getText().isEmpty());
    }

    @After
    public void cleanUp() {
        driver.close();
    }
}
