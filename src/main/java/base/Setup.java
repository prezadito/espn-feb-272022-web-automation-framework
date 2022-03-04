package base;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utility.Utilities;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Setup {

    public WebDriver driver;

    String userDir = System.getProperty("user.dir");

    public Properties prop = Utilities.loadProperties(userDir + "\\config.properties");
    String browserStackUsername = prop.getProperty("browserstack.username");
    String browserStackPassword = prop.getProperty("browserstack.password");
    String takeScreenshot = prop.getProperty("take.screenshot", "false");
    String waitTime = prop.getProperty("wait.time", "10");
    String windowMaximize = prop.getProperty("window.maximize", "false");

    public static com.relevantcodes.extentreports.ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (takeScreenshot.equalsIgnoreCase("true")){
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result.getName());
            }
        }
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    @Parameters({"useCloudEnv", "envName", "os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setup(@Optional("false") Boolean useCloudEnv, @Optional("Browserstack") String envName,
                      @Optional("Windows") String os,
                      @Optional("11") String os_version, @Optional("Chrome") String browserName,
                      @Optional("99") String browserVersion, @Optional("https://www.espn.com/") String url) throws MalformedURLException {
        if(useCloudEnv) {
            if(envName.equalsIgnoreCase("Browserstack")) {
                getCloudDriver(envName, browserStackUsername, browserStackPassword, os, os_version, browserName, browserVersion);
            } else if(envName.equalsIgnoreCase("Saucelabs")) {
                getCloudDriver(envName, browserStackUsername, browserStackPassword, os, os_version, browserName, browserVersion);
            }
        } else {
            getDriver(os, browserName);
        }

        driver.manage().timeouts().implicitlyWait(Long.parseLong(waitTime), TimeUnit.SECONDS);
        if(windowMaximize.equalsIgnoreCase("true")) {
            driver.manage().window().maximize();
        }

        driver.navigate().to(url);
    }

    public WebDriver getDriver(String os, String browserName) {
        if(browserName.equalsIgnoreCase("Chrome")) {
            if(os.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver.exe");
            } else if(os.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", userDir + "\\drivers\\chromedriver");
            }
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("Firefox")) {
            if(os.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", userDir + "\\drivers\\geckodriver.exe");
            } else if(os.equalsIgnoreCase("OS X")) {
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

    public void takeScreenshot(String screenshotName) {
        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+File.pathSeparator+ "screenshots" +File.pathSeparator+screenshotName+ " " +df.format(date)+".png"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            String path = System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png";
            System.out.println(path);
            System.out.println("Exception while taking screenshot " +e.getMessage());;
        }
    }

    public static String convertToString(String str){
        String splitString;
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(str), ' ');
        return splitString;
    }


}
