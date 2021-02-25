package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutUsPage {

    WebDriver driver;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/h2/u")
    private WebElement pageTitle;
    @FindBy(xpath="//*[@id=\"contact-title\"]")
    private WebElement contactUs;
    @FindBy(xpath="//*[@id=\"scrumlink\"]")
    private WebElement externalLink;
    public AboutUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPageTitle() {return pageTitle;}

    public void contactUsLink() { contactUs.click(); }

    public void clickExternalLink() {
       externalLink.click();
    }
}
