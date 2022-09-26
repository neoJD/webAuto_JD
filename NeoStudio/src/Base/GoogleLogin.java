package Base.NeoStudio.src.Base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class GoogleLogin {
    private WebElement element;
    private String google_email = "neo.jd@neolab.net";
    private String password = "whdejr11!";

    public static WebDriver driver;
    private String url;

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:\\chromedriver.exe";

    public GoogleLogin() {
        driver = new ChromeDriver();
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        url = "https://neostudiodev.web.app/"; // 네오스튜디오 dev 버전으로 url 변경
    }

    public void Login() throws InterruptedException {
            driver.get(url);

            driver.manage().window().maximize();

            Thread.sleep(3000);

            element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[1]/div[1]/button"));
            element.click();

            Thread.sleep(7000);

            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            Thread.sleep(2000);

            element = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
            element.sendKeys(google_email);

            element = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
            element.click();

            Thread.sleep(6000);
            element = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
            element.sendKeys(password);

            element = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span"));
            element.click();

            Thread.sleep(8000);

            driver.switchTo().window(tabs.get(0));

            WebElement popupClose = driver.findElement(By.cssSelector("#root > div > div.defaultLayout > div.jss68.jss72 > div > svg"));
            popupClose.click();
    }
}
