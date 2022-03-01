package test;

import base.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;

public class CheckScoreboardTest extends Setup {

    @Test
    public void checkNBAScores() {
        BasePage basePage = new BasePage(driver);
        basePage.hoverOverTopEvents(driver);
        basePage.clickOnTopEventOption("NFL");
        Assert.assertEquals(basePage.getTopEventsText(), "NFL");
    }

    //@Test
//    public void checkNBAScores2() {
//        Assert.assertEquals(getTitle(), "ESPN: Serving sports fans. Anytime. Anywhere.");
//        BasePage bp = new BasePage(driver);
//        hoverAndClick(bp.topEventsBtn, bp.dropdownNBA);
//        waitFor(3);
//        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/nba/'")).isDisplayed());
//    }



}
