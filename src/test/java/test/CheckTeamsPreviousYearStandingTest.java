package test;

import base.Setup;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.DenverBroncosClubhousePage;
import pages.NFLTeamsPage;
import pages.Standing2021Page;

public class CheckTeamsPreviousYearStandingTest extends Setup {

    @Test
    public void checkBroncos2020Standings() {
        BasePage basePage = new BasePage(driver);
        basePage.hoverOverNfl(driver);
        basePage.clickOnTeams(driver);
        NFLTeamsPage nflTeamsPage = new NFLTeamsPage(driver);
        nflTeamsPage.clickOnTeamBroncos(driver);
        DenverBroncosClubhousePage denverBroncosClubhousePage = new DenverBroncosClubhousePage(driver);
        denverBroncosClubhousePage.clickOnFullStandingLink();
        Standing2021Page standing2021Page = new Standing2021Page(driver);
        standing2021Page.select2020();
        waitFor(3);
    }

}
