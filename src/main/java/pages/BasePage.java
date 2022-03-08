package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage extends Setup {

    public BasePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button.button.button-filter.sm.dropdown-toggle.current-league-name")
    public WebElement topEventsBtn;

    @FindBy(css = "a[data-league='nba']")
    WebElement NBATopEventsOption;

    @FindBy(css = "button.current-league-name")
    WebElement selectedTopEvent;

    @FindBy(xpath = "//header/nav[@id='global-nav']/ul[1]/li[1]/a[1]/span[1]/span[1]")
    WebElement nflLink;

    @FindBy(xpath = "//header/nav[@id='global-nav']/ul[1]/li[1]/div[1]/ul[1]/li[7]/a[1]/span[1]/span[1]")
    WebElement teamsLink;

    @FindBy(xpath = "//li[@class='league']")
    List<WebElement> topEventsOptions;

    @FindBy(css = "li.pillar")
    List<WebElement> navPillarOptions;

    @FindBy(css = "li.pillar.espnplus")
    WebElement espnPlusNavBarLink;

    public void hoverOverTopEvents(WebDriver driver) {
        hoverOver(driver, topEventsBtn);
    }

    public void clickOnTopEventOption(String option) {
        clickOnElementInListWithGivenText(topEventsOptions, option);
    }

    public void clickOnNavPillarOption(String option) {
        clickOnElementInListWithGivenText(navPillarOptions, option);
    }

    public String getTopEventsText() {
        return getText(selectedTopEvent);
    }

    public void hoverOverNfl(WebDriver driver) {
        hoverOver(driver, nflLink);
    }

    public void clickOnTeams(WebDriver driver) {
        hoverAndClick(driver, teamsLink);
    }

    public void clickOnEspnPlusInNavBar() {
        click(espnPlusNavBarLink);
    }


}
