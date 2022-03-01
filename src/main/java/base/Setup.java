package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Setup {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/adity/IdeaProjects/espn-feb-272022-web-automation-framework/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.espn.com/");
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
