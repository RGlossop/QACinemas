package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FilmPage {

    @FindBy(xpath="//*[@id=\"more-info\"]/div/div[2]/div[1]/div[2]/div/p")
    private WebElement movieDescription;

    public FilmPage(WebDriver driver) {

    }

    public WebElement getDescription() {
        return movieDescription;
    }
}
