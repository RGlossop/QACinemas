package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScreensPage {

    @FindBy(xpath="//*[@id=\"page\"]/div[4]/div/h2")
    private WebElement screensType;
    @FindBy(id="screenselector")
    private WebElement screenSelector;
    @FindBy(xpath="//*[@id=\"screenselector\"]/option[2]")
    private WebElement premium;

    public ScreensPage(WebDriver driver) {

    }

    public WebElement checkType() {
        return screensType;
    }

    public void selectPremium() {
        screenSelector.click();
        premium.click();
    }
}
