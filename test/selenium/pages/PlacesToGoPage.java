package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlacesToGoPage {
    @FindBy(xpath="//*[@id=\"page\"]/section/div[1]/div[1]/div[1]/h5")
    private WebElement firstCardTitle;

    public PlacesToGoPage(WebDriver driver) {

    }

    public WebElement getFirstCardTitle() { return firstCardTitle; }

}
