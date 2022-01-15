import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoJunit {
    WebDriver driver;
    By bannerImage = By.className("banner-image");
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headed");
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();

        //Assertion
        Assert.assertEquals("ToolsQA", title);
    }

    @Test
    public void checkImage(){
        driver.get("https://demoqa.com/");
        boolean imageElement = driver.findElement(bannerImage).isDisplayed();

        //Assertion
        Assert.assertTrue(imageElement);

    }
    @After
    public void closeBrowser(){
        driver.quit();
    }
}

