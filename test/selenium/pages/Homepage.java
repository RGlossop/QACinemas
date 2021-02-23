package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
    private final String URL = "http://localhost:9000/";

    //Nav Elements
    @FindBy(xpath = "//*[@id=\"navbarNavLeft\"]/ul/li[2]/a")
    private WebElement navNewReleases;
    @FindBy(xpath= "//*[@id=\"navbarNavLeft\"]/ul/li[3]/a")
    private WebElement navMovieForum;
    @FindBy(xpath="//*[@id=\"navbarNavRight\"]/ul/li[2]/a")
    private WebElement navSignUp;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[5]/a")
    private WebElement navExpandButton;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[1]/a")
    private WebElement navAllFilms;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[2]/a")
    private WebElement navOurScreens;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[3]/a")
    private WebElement navPlacesToGo;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[4]/a")
    private WebElement navClassifications;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[5]/a")
    private WebElement navAboutUs;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[6]/a")
    private WebElement navContactUs;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[5]/ul/li[7]/a")
    private WebElement navFindUs;

    //Pages
    public NewReleasesPage newReleases;
    public MovieForumPage movieForum;
    public ListingsGalleryPage listingsGallery;
    public ScreensPage screens;
    public FilmPage film;
    public PlacesToGoPage places;
    public ClassificationsPage classifications;
    public AboutUsPage aboutUs;
    public ContactUsPage contactUs;
    public FindUsPage findUs;
    public SignUpPage signUp;
    //elements
    @FindBy(xpath="//*[@id=\"mainheader\"]")
    private WebElement title;

    public Homepage(WebDriver driver) {
        driver.get(URL);
        newReleases = PageFactory.initElements(driver, NewReleasesPage.class);
        movieForum = PageFactory.initElements(driver, MovieForumPage.class);
        listingsGallery = PageFactory.initElements(driver, ListingsGalleryPage.class);
        screens = PageFactory.initElements(driver, ScreensPage.class);
        film = PageFactory.initElements(driver, FilmPage.class);
        places = PageFactory.initElements(driver, PlacesToGoPage.class);
        classifications = PageFactory.initElements(driver, ClassificationsPage.class);
        aboutUs = PageFactory.initElements(driver, AboutUsPage.class);
        contactUs = PageFactory.initElements(driver, ContactUsPage.class);
        findUs = PageFactory.initElements(driver, FindUsPage.class);
        signUp = PageFactory.initElements(driver, SignUpPage.class);
    }
    //NavStuff
    public void navNewReleases() {
        navNewReleases.click();
    }
    public void navMovieForum() {
        navMovieForum.click();
    }
    public void navSignUp() { navSignUp.click(); }
    public void navExpand() {
        navExpandButton.click();
    }
    public void navAllFilms() {
        navExpand();
        navAllFilms.click();
    }
    public void navOurScreens() {
        navExpand();
        navOurScreens.click();
    }
    public void navPlacesToGo() {
        navExpand();
        navPlacesToGo.click();
    }
    public void navClassifications() {
        navExpand();
        navClassifications.click();
    }
    public void navAboutUs() {
        navExpand();
        navAboutUs.click();
    }
    public void navContactUs() {
        navExpand();
        navContactUs.click();
    }
    public void navFindUs() {
        navExpand();
        navFindUs.click();
    }
    // elements
    public WebElement getTitle() {
        return title;
    }
}
