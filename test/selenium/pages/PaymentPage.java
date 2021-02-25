package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage {
    @FindBy(xpath="//*[@id=\"page\"]/div[3]/div/div[2]/div/a")
    private WebElement submit;

    public PaymentPage(WebDriver driver) {

    }

    public void acceptOrder() {
        submit.click();
    }
}
