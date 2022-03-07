package test;

import base.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.VideoPlayerPage;

public class PlayHomePageVideoTest extends Setup {

    @Test
    public void WatchNbaOnEspnLiveTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickPlayVideoButton();
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        Assert.assertTrue(videoPlayerPage.isChooseProviderPopUpVisible(driver));
        videoPlayerPage.closeCableProviderWindow();
        Assert.assertFalse(videoPlayerPage.isChooseProviderPopUpVisible(driver));
        videoPlayerPage.playKnicksVsClippersVideo();
        Assert.assertTrue(videoPlayerPage.isChooseProviderPopUpVisible(driver));
    }
}
