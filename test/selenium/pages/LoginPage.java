package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(xpath="//*[@id=\"username1\"]")
    private WebElement usernameText;
    @FindBy(xpath="//*[@id=\"password1\"]")
    private WebElement passwordText;

    @FindBy(xpath="//*[@id=\"loginSubmit\"]")
    private WebElement bLogin;
    @FindBy(xpath="//*[@id=\"login2signup\"]")
    private WebElement bRegister;

    public LoginPage(WebDriver driver) {

    }

    public void login(String username, String password){
        usernameText.sendKeys(username);
        passwordText.sendKeys(password);
        bLogin.click();
    }
    public void adminLogin() {
        usernameText.sendKeys("Admin123");
        passwordText.sendKeys("Password123");
        bLogin.click();
    }
}
