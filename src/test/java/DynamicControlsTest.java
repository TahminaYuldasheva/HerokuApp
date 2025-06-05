import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DynamicControlsTest {
    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        boolean isFieldDisabled = driver.findElement(By.xpath("//*[@type='text']")).isEnabled();
        assertFalse(isFieldDisabled, "Поле доступно для ввода!");
        driver.findElement(By.xpath("//*[text()='Enable']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        boolean isFieldEnabled = driver.findElement(By.xpath("//*[@type='text']")).isEnabled();
        assertTrue(isFieldEnabled, "Поле недоступно для ввода!");
        driver.quit();
    }
}
