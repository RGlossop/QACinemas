package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AboutUsPage {

    WebDriver driver;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/h1[1]/u")
    private WebElement pageTitle;
    @FindBy(xpath="//*[@id=\"contact-title\"]")
    private WebElement contactUs;
    @FindBy(xpath="/html/body/div/div[3]/div/div[3]/div[1]/p[3]/a")
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
