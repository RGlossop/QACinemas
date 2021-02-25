package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage {

    @FindBy(xpath="//*[@id=\"sender\"]")
    private WebElement emailText;
    @FindBy(xpath="//*[@id=\"subject\"]")
    private WebElement subjectText;
    @FindBy(xpath="//*[@id=\"body\"]")
    private WebElement messageText;
    @FindBy(xpath="//*[@id=\"greybackgroundform\"]/button")
    private WebElement bSendEmail;

    public ContactUsPage(WebDriver driver) {

    }

    public void writeEmail(String email, String subject, String message) {
        emailText.sendKeys(email);
        subjectText.sendKeys(subject);
        messageText.sendKeys(message);
        bSendEmail.click();
    }
}
