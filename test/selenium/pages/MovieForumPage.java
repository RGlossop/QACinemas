package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MovieForumPage {

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/section[1]")
    private WebElement comments;
    @FindBy(xpath="//*[@id=\"username\"]")
    private WebElement usernameField;
    @FindBy(xpath="//*[@id=\"comment_body\"]")
    private WebElement commentField;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/section[2]/div/form/button")
    private WebElement bSubmitComment;
    public MovieForumPage(WebDriver driver) {

    }

    public void addComment(String username, String comment) throws InterruptedException {
        usernameField.sendKeys(username);
        commentField.sendKeys(comment);
        bSubmitComment.click();
    }

    public WebElement getComments() {
        return comments;
    }
}
