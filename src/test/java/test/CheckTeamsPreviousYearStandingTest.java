package test;

import base.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckTeamsPreviousYearStandingTest extends Setup {

    @Test
    public void checkBroncos2020Standings() {
        BasePage basePage = new BasePage(driver);
        basePage.hoverOverNfl(driver);
        basePage.clickOnTeams(driver);
        Assert.assertEquals(getTitle(), "NFL Teams | ESPN");
        NFLTeamsPage nflTeamsPage = new NFLTeamsPage(driver);
        nflTeamsPage.clickOnTeamBroncos(driver);
        Assert.assertEquals(getCurrentUrl(), "https://www.espn.com/nfl/team/_/name/den/denver-broncos");
        DenverBroncosClubhousePage denverBroncosClubhousePage = new DenverBroncosClubhousePage(driver);
        denverBroncosClubhousePage.clickOnFullStandingLink();
        Assert.assertEquals(getCurrentUrl(), "https://www.espn.com/nfl/standings");
        Standing2021Page standing2021Page = new Standing2021Page(driver);
        Assert.assertEquals(standing2021Page.getStandings2021HeaderText(), "NFL Standings 2021");
        standing2021Page.select2020();
        Standing2020Page standing2020Page = new Standing2020Page(driver);
        Assert.assertEquals(standing2020Page.getStandings2020HeaderText(), "NFL Standings 2020");
        waitFor(3);
    }

}
