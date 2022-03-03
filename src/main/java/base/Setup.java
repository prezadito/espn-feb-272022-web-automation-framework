package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Setup {

    public WebDriver driver;

    String userDir = System.getProperty("user.dir");

    @Parameters({"useCloudEnv", "envName", "envUsername", "envAccessKey", "os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setup(@Optional("false") Boolean useCloudEnv, @Optional("Browserstack") String envName,
                      @Optional String envUsername, @Optional String envAccessKey, @Optional("Windows") String os,
                      @Optional("11") String os_version, @Optional("Chrome") String browserName,
                      @Optional("99") String browserVersion, @Optional("https://www.espn.com/") String url) throws MalformedURLException {
        if(useCloudEnv) {
            if(envName.equalsIgnoreCase("Browserstack")) {
                getCloudDriver(envName, envUsername, envAccessKey, os, os_version, browserName, browserVersion);
            } else if(envName.equalsIgnoreCase("Saucelabs")) {
                getCloudDriver(envName, envUsername, envAccessKey, os, os_version, browserName, browserVersion);
            }
        } else {
            getDriver(os, browserName);
        }

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }

    public WebDriver getDriver(String os, String browserName) {
        if(browserName.equalsIgnoreCase("Chrome")) {
            if(os.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver.exe");
            } else if(browserName.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver");
            }
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("Firefox")) {
            if(os.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", userDir + "\\drivers\\geckodriver.exe");
            } else if(browserName.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.gecko.driver", userDir + "\\drivers\\geckodriver");
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os,
                                    String os_version, String browserName, String browserVersion) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if(envName.equalsIgnoreCase("Saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey + "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if(envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey + "@hub-cloud.browserstack.com:80/wd/hub"), cap);
        }
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        // waitFor(3);
        driver.quit();
    }

    public void click(WebElement element) {
        element.click();
    }

    public void hoverOver(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void hoverAndClick(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void clickOnElementInListWithGivenText(List<WebElement> elements, String text){
        for (WebElement element: elements) {
            if(element.getText().equalsIgnoreCase(text)){
                element.click();
            }
        }
    }

    public void scrollToView(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void selectFromDropdown(WebElement element, String option) {
        Select select = new Select(element);
        try {
            select.selectByVisibleText(option);
        } catch(Exception exception) {
            select.selectByValue(option);
        }
    }

}
