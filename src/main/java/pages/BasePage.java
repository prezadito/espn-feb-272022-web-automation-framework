package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage extends Setup {

    public BasePage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.scores-next.controls")
    public WebElement nextScore;

    @FindBy(css = "div.scores-prev.controls")
    public WebElement prevScore;

    @FindBy(css = "button.button.button-filter.sm.dropdown-toggle.current-league-name")
    public WebElement topEventsBtn;

    @FindBy(css = "a[data-league='nba']")
    public WebElement dropdownNBA;

    @FindBy(css = "a[href='/']")
    WebElement mainLogo;

    @FindBy(css = "a[href='/nfl/'")
    WebElement navNFLBtn;

    @FindBy(css = "a[href='/nba/'")
    WebElement navNBABtn;

    @FindBy(css = "a[href='/nhl/'")
    WebElement navNHLBtn;

    @FindBy(css = "a[href='/mens-college-basketball/'")
    WebElement navNCAAMBtn;

    @FindBy(css = "a[href='/womens-college-basketball/'")
    WebElement navNCAAWBtn;

    @FindBy(css = "a[href='/soccer/'")
    WebElement navSoccerBtn;

    @FindBy()
    WebElement navItemDrop;

    @FindBy(css = "li.pillar.logo.espnplus")
    WebElement navESPNPlus;

    @FindBy(css = "a[href='http://www.espn.com/watch/']")
    WebElement navWatch;

    @FindBy(css = "a[href='http://www.espn.com/espnradio/index']")
    WebElement navListen;

    @FindBy(css = "a[href='/fantasy/'")
    WebElement navFantasy;

    @FindBy()
    WebElement navMore;

    @FindBy(css = "a#globalsearchtrigger")
    WebElement navSearch;

    @FindBy(css = "a#global-user-trigger")
    WebElement navProfile;

    public void dropdown(WebElement ele, WebElement ele2) {
        click(ele);
        click(ele2);
    }




}
