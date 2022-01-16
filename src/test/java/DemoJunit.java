import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoJunit {
    WebDriver driver;
    By bannerImage = By.className("banner-image");
    By fullName = By.id("userName");
    By userEmail = By.id("userEmail");
    By currentAddress = By.cssSelector("textarea[placeholder='Current Address']");
    By permanentAddress = By.id("permanentAddress");
    By submitBtn = By.id("submit");
    By result_name = By.id("name");
    By result_email = By.id("email");
    By result_currentAddress = By.id("currentAddress");
    By result_permanentAddress = By.id("permanentAddress");
    By seeAlert = By.id("alertButton");

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headed");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();

        //Assertion on title
        Assert.assertEquals("ToolsQA", title);
    }

    @Test
    public void checkImage(){
        driver.get("https://demoqa.com/");
        boolean imageElement = driver.findElement(bannerImage).isDisplayed();

        //Assertion
        Assert.assertTrue(imageElement);

    }
    @Test
    public void submitTextBox(){
        driver.get("https://demoqa.com/text-box");
        String nameValue = "This is full name";
        String emailValue = "test@email.com";
        String currentAddressValue = "Uttara";
        String permanentAddressValue = "Dhaka";

        driver.findElement(fullName).sendKeys(nameValue);
        driver.findElement(userEmail).sendKeys(emailValue);
        driver.findElement(currentAddress).sendKeys(currentAddressValue);
        driver.findElement(permanentAddress).sendKeys(permanentAddressValue);
        WebElement webElement = driver.findElement(submitBtn);

        //Scroll and click
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView();", webElement);
        webElement.click();


        String output_name= driver.findElement(result_name).getText();
        String output_email= driver.findElement(result_email).getText();
        String output_cAddress= driver.findElement(result_currentAddress).getText();
        String output_pAddress= driver.findElement(result_permanentAddress).getText();

        Assert.assertEquals("Name:"+nameValue, output_name);
        Assert.assertEquals("Email:"+emailValue, output_email);
        //Assert.assertEquals("Current Address:"+currentAddressValue, output_cAddress);
        //Assert.assertEquals("Permananet Address:"+permanentAddressValue, output_pAddress);

    }

    @Test
    public void clickAlert(){
        driver.get("https://demoqa.com/alerts");

        driver.findElement(seeAlert).click();
        Assert.assertEquals("You clicked a button", "You clicked a button");
        driver.switchTo().alert().accept();
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }
}

