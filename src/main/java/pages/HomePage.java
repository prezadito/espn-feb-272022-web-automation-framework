package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Setup {

    public HomePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='video-play-button video-play-button--onefeed live']")
    WebElement videoPlayButton;

    public void clickPlayVideoButton() {
        click(videoPlayButton);
        waitFor(5);
    }

}
