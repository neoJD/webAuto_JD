package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;


public class GoogleLogin {
    //private String winHandleBefore;
    private WebElement element;
    private String google_email = "sykim@neolab.net";
    private String password = "rlatndus505!";
    public static WebDriver driver;
    private String url;
    private static String relatedURL;

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";

    public GoogleLogin() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();

        url = "https://neostudio-staging.web.app/";
    }

    public void Login() {
        try {
            driver.get(url);

            Thread.sleep(3000);

            element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[1]/div[1]/button"));
            element.click();

            Thread.sleep(7000);

            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            element = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
            element.sendKeys(google_email);

            element = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
            element.click();

            Thread.sleep(4000);
            element = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
            element.sendKeys(password);

            element = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span"));
            element.click();

            Thread.sleep(7000);

            driver.switchTo().window(tabs.get(0));

            WebElement popupClose = driver.findElement(By.cssSelector("#root > div > div.defaultLayout > div.jss68.jss72 > div > svg"));
            popupClose.click();


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            //driver.close();
        }
    }
}
