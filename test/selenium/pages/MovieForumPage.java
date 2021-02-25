package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MovieForumPage {
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/table/tbody/tr[1]/th/a")
    private WebElement darkKnightLink;

    @FindBy(xpath="//*[@id=\"rating\"]")
    private WebElement ratingBox;
    @FindBy(xpath="//*[@id=\"rating\"]/option[2]")
    private WebElement ratingChoice;
    @FindBy(xpath="//*[@id=\"comment_body\"]")
    private WebElement comment;
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div/div/form/button")
    private WebElement commentSubmit;

    @FindBy(xpath="//*[@id=\"page\"]/div[3]/table/tbody/tr[1]/th")
    private WebElement commentPoster;

    public MovieForumPage(WebDriver driver) {

    }

    public void addComment(String comment) throws InterruptedException {
        this.comment.sendKeys(comment);
        ratingBox.click();
        ratingChoice.click();
        ratingBox.click();
        commentSubmit.click();
    }

    public void selectFilm() {
        darkKnightLink.click();
    }
    public WebElement getCommentPoster() {
        return commentPoster;
    }
}
