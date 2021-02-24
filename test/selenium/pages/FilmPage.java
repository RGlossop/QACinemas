package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilmPage {

    @FindBy(xpath="//*[@id=\"more-info\"]/div/div[2]/div[1]/div[2]/div/p")
    private WebElement movieDescription;
    @FindBy(xpath="//*[@id=\"send2booking\"]")
    private WebElement bBookFilm;
    public FilmPage(WebDriver driver) {

    }

    public void goToBookings() { bBookFilm.click(); }
    public WebElement getDescription() {
        return movieDescription;
    }
}
