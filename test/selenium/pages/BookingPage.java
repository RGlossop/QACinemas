package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingPage {

    @FindBy(xpath="//*[@id=\"adult_tickets\"]")
    private WebElement adultTickets;
    @FindBy(xpath="//*[@id=\"children_tickets\"]")
    private WebElement childrenTickets;
    @FindBy(xpath="//*[@id=\"concession_tickets\"]")
    private WebElement concessionTickets;
    @FindBy(xpath="//*[@id=\"bookingform\"]/button")
    private WebElement bSubmitOrder;

    public BookingPage(WebDriver driver) {

    }

    public void bookTicket(String adultTickets, String childrenTickets, String concessionTickets) {
        this.adultTickets.sendKeys(adultTickets);
        this.childrenTickets.sendKeys(childrenTickets);
        this.concessionTickets.sendKeys(concessionTickets);
        bSubmitOrder.click();
    }
}
