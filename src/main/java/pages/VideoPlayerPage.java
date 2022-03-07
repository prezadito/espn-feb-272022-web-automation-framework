package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VideoPlayerPage extends Setup {

    public VideoPlayerPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='lightbox__closebtn__wrap flex items-center justify-center']//*[name()='svg']")
    WebElement cableProviderCloseButton;

    @FindBy(xpath = "//img[@alt='New York Knicks vs. Los Angeles Clippers']")
    WebElement knicksVsClippersVideo;

    public void playKnicksVsClippersVideo() {
        click(knicksVsClippersVideo);
    }

    public void closeCableProviderWindow() {
        click(cableProviderCloseButton);
    }

    public Boolean isChooseProviderPopUpVisible(WebDriver driver) {
        return checkPageForElement(driver, "Choose Provider");
    }

}
