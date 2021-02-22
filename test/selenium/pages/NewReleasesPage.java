package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewReleasesPage {

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div/div[1]/div/a/div/h5")
    private WebElement firstFilmName;

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div/div[1]/div/a/img")
    private WebElement filmImage;

    public NewReleasesPage(WebDriver driver) {

    }

    public WebElement getFirstFilmName() {
        return firstFilmName;
    }

    public void clickFilm() {
        filmImage.click();
    }
}
