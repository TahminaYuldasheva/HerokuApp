import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTypos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        for (int i = 0; i < 10; i++) {
            driver.navigate().refresh();
            String actualText = driver.findElements(By.tagName("p")).get(1).getText();
            Assert.assertEquals(
                    actualText,
                    "Sometimes you'll see a typo, other times you won't.",
                    "Обнаружена опечатка в тексте. Ошибка в won,t.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

