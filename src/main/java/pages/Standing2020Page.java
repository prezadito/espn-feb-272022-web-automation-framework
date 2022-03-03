package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Standing2020Page extends Setup {

    public Standing2020Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'NFL Standings 2020')]")
    WebElement standings2020Header;

    public String getStandings2020HeaderText(){
        return getText(standings2020Header);
    }

}
