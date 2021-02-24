package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {

    //formElements
    @FindBy(xpath="//*[@id=\"first_name\"]")
    private WebElement fNameText;
    @FindBy(xpath="//*[@id=\"last_name\"]")
    private WebElement sNameText;
    @FindBy(xpath="//*[@id=\"date_of_birth\"]")
    private WebElement dobText;
    @FindBy(id="username")
    private WebElement usernameText;
    @FindBy(id="email")
    private WebElement emailText;
    @FindBy(id="password")
    private WebElement passwordText;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/form/button")
    private WebElement bSignUp;

    public SignUpPage(WebDriver driver) {

    }

    public void signUp(String fName, String sName, String dob, String username, String email, String password) throws InterruptedException {
        dobText.sendKeys(dob);
        fNameText.sendKeys(fName);
        sNameText.sendKeys(sName);
        usernameText.sendKeys(username);
        emailText.sendKeys(email);
        passwordText.sendKeys(password);
        bSignUp.click();
    }

}
