package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SetUp {

    public WebDriver driver;

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "../drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.espn.com/");
    }

    public void tearDown() {
        driver.quit();
    }

    public void click(WebElement element) {
        element.click();
    }
}
