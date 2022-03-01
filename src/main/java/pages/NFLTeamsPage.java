package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NFLTeamsPage extends Setup {

    public NFLTeamsPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//h2[contains(text(),'Denver Broncos')]")
    WebElement denverBroncosTeamLink;

    public void clickOnTeamBroncos(WebDriver driver) {
        scrollToView(driver, denverBroncosTeamLink);
        click(denverBroncosTeamLink);
    }



}
