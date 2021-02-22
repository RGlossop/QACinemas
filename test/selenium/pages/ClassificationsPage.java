package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClassificationsPage {

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div[1]/div[1]/div/div/h5")
    private WebElement firstCardTitle;

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div[1]/div[1]/div/a/img")
    private WebElement firstCardImage;

    public ClassificationsPage(WebDriver driver) {

    }

    public WebElement getFirstCardTitle() { return firstCardTitle; }

    public void clickFirstCard() {firstCardImage.click();}
}
