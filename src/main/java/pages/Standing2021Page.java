package pages;

import base.Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Standing2021Page extends Setup {

    public Standing2021Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'NFL Standings 2021')]")
    WebElement standings2021Header;

    @FindBy(xpath = "//body/div[@id='espnfitt']/div[@id='DataWrapper']/div[@id='fitt-analytics']/div[1]/div[4]/div[3]/div[1]/div[1]/section[1]/div[1]/section[1]/div[2]/div[1]/section[1]/section[1]/section[1]/div[1]/select[1]")
    WebElement yearDropdown;

    public String getStandings2021HeaderText(WebElement element){
        return getText(element);
    }

    public void select2020() {
        selectFromDropdown(yearDropdown, "2020");
    }


}
