package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {

    @FindBy(xpath="//*[@id=\"searchText\"]")
    private WebElement searchTerm;
    @FindBy(xpath="//*[@id=\"navbarNavCenter\"]/div[2]/form/div/div[2]/input")
    private WebElement searchSubmit;

    @FindBy(xpath="//*[@id=\"searchresults\"]/div/a/h3")
    private WebElement searchResult;

    public SearchPage(WebDriver driver) {

    }

    public void search(String term, WebDriver driver) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].scrollIntoView()", searchTerm);
        Thread.sleep(1000);
        searchTerm.sendKeys(term);
        searchSubmit.click();
    }
    public WebElement getSearchResult() { return searchResult; }
}
