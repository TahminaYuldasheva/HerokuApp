import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInputs() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.findElement(By.tagName("input")).sendKeys("1");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        String value = driver.findElement(By.tagName("input")).getAttribute("value");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(value, "2", "Неверный результат!");

        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        String secondValue = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(secondValue, "1", "Неверный результат!");


        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys("abcxyz");
        String invalidInput = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(invalidInput, "");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
