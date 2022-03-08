package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WatchEspnPage extends Setup {

    public WatchEspnPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='CarouselSlide relative pointer CarouselSlide--active CarouselSlide--inView']//div[@class='MediaPlaceholder relative MediaPlaceholder--16x9 cursor-pointer MediaPlaceholder--live MediaPlaceholder--button-hover WatchTile__Image WatchTile__Image--16x9']//img[@aria-hidden='false']")
    WebElement featuredVideo;

    @FindBy(xpath = "//button[@class='lightbox__closebtn absolute overflow-hidden lightbox__closebtn--dark']")
    WebElement contentPaywallCloseButton;

    @FindBy(xpath = "//div[@data-testid='paywall-content-cta-content']")
    WebElement contentPaywall;

    @FindBy(css = ".embed-player__iframe")
    WebElement contentPaywallFrameElement;

    public Boolean isContentPaywallVisible(WebDriver driver) {
        //switchToIFrame(driver, 0);
        driver.switchTo().defaultContent();
        waitUntilFrameIsAvailableAndSwitchToIt(driver, contentPaywallFrameElement);
        return checkIfElementIsDisplayed(contentPaywall);
    }

    public void playFeaturedVideo(WebDriver driver) {
        waitUntilElementIsClickableThenClickIt(driver, featuredVideo);
    }

    public void closeContentPaywall(WebDriver driver) {
        //switchToIFrame(driver, 0);
        driver.switchTo().defaultContent();
        waitUntilFrameIsAvailableAndSwitchToIt(driver, contentPaywallFrameElement);
        click(contentPaywallCloseButton);
    }

}
