package selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
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
    @FindBy(xpath="//*[@id=\"navbarNavRight\"]/ul/li[1]/a")
    private WebElement navSignUp;
    @FindBy(xpath="//*[@id=\"navbarNavRight\"]/ul/li[2]/a")
    private WebElement navLogin;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[3]/a")
    private WebElement navExpandButton;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[1]/a")
    private WebElement navAllFilms;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[2]/a")
    private WebElement navOurScreens;
    @FindBy(xpath= "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[3]/a")
    private WebElement navPlacesToGo;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[4]/a")
    private WebElement navClassifications;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[5]/a")
    private WebElement navAboutUs;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[6]/a")
    private WebElement navContactUs;
    @FindBy(xpath = "//*[@id=\"navbarNavRight\"]/ul/li[3]/ul/li[7]/a")
    private WebElement navFindUs;
    //Bottom nav
    @FindBy(xpath="//*[@id=\"navbarNavCenter\"]/div[1]/ul/li[1]/a")
    private WebElement bNavAboutUs;
    @FindBy(xpath="//*[@id=\"navbarNavCenter\"]/div[1]/ul/li[2]/a")
    private WebElement bNavContactUs;
    @FindBy(xpath="//*[@id=\"navbarNavCenter\"]/div[1]/ul/li[3]/a")
    private WebElement bNavFindUs;
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
    public LoginPage login;
    public BookingPage bookingPage;
    public PaymentPage payment;
    public SearchPage search;
    //elements
    @FindBy(xpath="//*[@id=\"mainheader\"]")
    private WebElement title;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div[1]/div/p[1]")
    private WebElement firstLine;

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
        login = PageFactory.initElements(driver, LoginPage.class);
        bookingPage = PageFactory.initElements(driver, BookingPage.class);
        payment = PageFactory.initElements(driver, PaymentPage.class);
        search = PageFactory.initElements(driver, SearchPage.class);
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
    public void navLogin() {
        navLogin.click();
    }
    //Bottom nav
    public void bNavAboutUs(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].scrollIntoView()", bNavAboutUs);
        Thread.sleep(1000);
        bNavAboutUs.click();
    }
    public void bNavContactUs(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].scrollIntoView()", bNavContactUs);
        Thread.sleep(1000);
        bNavContactUs.click();
    }
    public void bFindUs(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].scrollIntoView()", bNavFindUs);
        Thread.sleep(1000);
        bNavFindUs.click();
    }
    // elements
    public WebElement getTitle() {
        return title;
    }

    public WebElement getFirstLine() {
        return firstLine;
    }
    public WebElement getNavLogin() {return navLogin;}


}
