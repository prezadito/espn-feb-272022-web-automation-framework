package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.scores-next.controls")
    WebElement nextScore;

    @FindBy(css = "div.scores-prev.controls")
    WebElement prevScore;
}
