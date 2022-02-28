package test;

import base.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;

public class CheckScoreboardTest extends Setup {

    @Test
    public void checkNBAScores() {
        Assert.assertEquals(getTitle(), "ESPN: Serving sports fans. Anytime. Anywhere.");
        BasePage bp = new BasePage(driver);
        bp.dropdown(bp.topEventsBtn, bp.dropdownNBA);
        waitFor(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("a[href='/nba/'")).isDisplayed());
    }



}
