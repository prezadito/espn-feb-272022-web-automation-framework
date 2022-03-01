package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DenverBroncosClubhousePage extends Setup {

    public DenverBroncosClubhousePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Full Standings')]")
    WebElement fullStandingLink;

    @FindBy(xpath = "//body/div[@id='espnfitt']/div[@id='DataWrapper']/div[@id='fitt-analytics']/div[1]/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/h1[1]")
    WebElement denverBroncosClubhouseHeader;

    public String getDenverBroncosClubhouseHeader() {
        return getText(denverBroncosClubhouseHeader);
    }

    public void clickOnFullStandingLink() {
        click(fullStandingLink);
    }
}
