package test;

import base.Setup;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.WatchEspnPage;

public class PlayVideoTest extends Setup {

    //@Test
//    public void WatchNbaOnEspnLiveTest() {
//        HomePage homePage = new HomePage(driver);
//        homePage.clickPlayVideoButton();
//        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
//        Assert.assertTrue(videoPlayerPage.isChooseProviderPopUpVisible(driver));
//        videoPlayerPage.closeCableProviderWindow();
//        Assert.assertFalse(videoPlayerPage.isChooseProviderPopUpVisible(driver));
//        videoPlayerPage.playKnicksVsClippersVideo();
//        Assert.assertTrue(videoPlayerPage.isChooseProviderPopUpVisible(driver));
//    }

    @Test
    public void WatchFeaturedVideoOnEspnPlusTest() {
        BasePage basePage = new BasePage(driver);
        basePage.clickOnEspnPlusInNavBar();
        Assert.assertEquals(getTitle(), "ESPN+ | Videos | Watch ESPN");
        WatchEspnPage watchEspnPage = new WatchEspnPage(driver);
        watchEspnPage.playFeaturedVideo(driver);
        //Assert.assertTrue(watchEspnPage.isContentPaywallVisible(driver));
        watchEspnPage.closeContentPaywall(driver);
//        Assert.assertFalse(watchEspnPage.isContentPaywallVisible(driver));
//        watchEspnPage.playFeaturedVideo(driver);
//        Assert.assertTrue(watchEspnPage.isContentPaywallVisible(driver));
    }
}
