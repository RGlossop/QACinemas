package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage {
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div[2]/h2")
    private WebElement emailHeader;

    @FindBy(xpath="//*[@id=\"sender\"]")
    private WebElement emailText;
    @FindBy(xpath="//*[@id=\"subject\"]")
    private WebElement subjectText;
    @FindBy(xpath="//*[@id=\"body\"]")
    private WebElement messageText;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div[2]/form/button")
    private WebElement bSendEmail;

    public ContactUsPage(WebDriver driver) {

    }

    public WebElement getEmailHeader() { return emailHeader; }

    public void writeEmail(String email, String subject, String message) {
        emailText.sendKeys(email);
        subjectText.sendKeys(subject);
        messageText.sendKeys(message);
        bSendEmail.click();
    }
}
