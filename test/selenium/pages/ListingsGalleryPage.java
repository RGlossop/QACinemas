package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListingsGalleryPage {

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div/div[1]/div/div/a/div[1]")
    private WebElement listing1;

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div/div[1]/div/div/a/img")
    private WebElement filmImage;

    public ListingsGalleryPage(WebDriver driver) {

    }

    public WebElement getListing1() { return listing1; }

    public void clickFilm() {
        filmImage.click();
    }

}
