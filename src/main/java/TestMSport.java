import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestMSport {
    static WebDriver driver;
    static String browser = "opera";

    public static void main(String[] args) {
        if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            System.out.println("Your set incorrect name browser");
        }

        driver.manage().window().maximize();
        driver.get("https://rn-sport.com.ua/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, 1 / 3);

        // dropdown Каталог товаров
        driver.findElement(By.xpath("//*[@data-id='header-nav-categories']")).click();
        List<WebElement> dropdown = driver.findElements(By.xpath("//*[@id='header-nav-categories']//a[@class='cat-menu__el-link']"));
        System.out.println("The number of products in the catalog: " + dropdown.size());

        // Getting links
        for (WebElement link : dropdown) {
            System.out.println(link.getText() + "  ---url's---  " + link.getAttribute("href"));
        }

        // First category selection
        dropdown.get(0).click();

        // Set checkboxes all manufacturers
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-code='brend']/div/div"))).click();

        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='filter-body']/div[3]/div//label"));
        System.out.println(elements.size());


        for (int i = 0; i < elements.size(); i++) {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            elements.get(i).click();
            js.executeScript("window.scrollBy(0, 24)");

        }

    }
}
