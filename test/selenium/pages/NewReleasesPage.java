package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewReleasesPage {


    @FindBy(xpath="//*[@id=\"filmcard\"]/a/img")
    private WebElement filmImage;

    public NewReleasesPage(WebDriver driver) {

    }


    public void clickFilm() {
        filmImage.click();
    }
}
