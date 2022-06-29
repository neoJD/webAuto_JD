package Base;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckURL {
    private WebDriver element;
    public static WebDriver driver;
    public static String link = "";
    static HttpURLConnection huc = null;
    public static int respCode = 0;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "/Users/sykim/Desktop/chromedriver";
    private static String url = "https://www.neostudio.io/policy/de";
    public static ArrayList<String> urlList = new ArrayList<>();

    public static void setUrlList(){

    }

    public static void url_check() throws InterruptedException {
        urlList.add("https://www.naver.com");
        urlList.add("https://www.neostudio.io/policy/de");

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        driver = new ChromeDriver();

        try {
            driver.get(urlList.get(0));
            driver.manage().window().maximize();

            for(int i=0; i<urlList.size(); i++) {
                huc = (HttpURLConnection) (new URL(urlList.get(i)).openConnection());
                huc.connect();
                respCode = huc.getResponseCode();

                if (respCode >= 400) {
                    System.out.println("HTML 에러 발생 : " + driver.getCurrentUrl() + " > 에러 코드 : " + respCode);
                }
                else {
                    System.out.println("페이지 정상 진입 : " + driver.getCurrentUrl());
                }
                for(int k=1; k<urlList.size(); k++) {
                    driver.navigate().to(urlList.get(k));
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            Thread.sleep(3000);
            driver.close();

        }
    }
}


