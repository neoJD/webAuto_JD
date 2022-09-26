package Base.NeoStudio.src.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static Base.GoogleLogin.*;

public class Scroll extends MainMenu {
    public static void Scroll(WebElement Mainmenu) {
        // timeline에 있는 모든 페이지 정보를 불러오는 메소드
        try {

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            Mainmenu.click(); // 파라미터로 받은 메인메뉴 또는 웹엘리먼트 클릭

            // 자바스크립트 0부터, 페이지끝까지 스크롤 다운
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void TimeLine_Scrolldown(WebElement TimeLine) {
        // 타임라인 전용 스크롤다운
        try {

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            TimeLine.click(); // 파라미터는 타임라인

             driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[2]/div/div[1]/h1[1]/b")).click(); // timeline 한정

            JavascriptExecutor js = (JavascriptExecutor) driver;
            // 현재까지 최대 스크롤 길이 pageHeight

            while (true) {
                // 페이지 끝까지 스크롤다운
                long pageHeight = (long) js.executeScript
                        ("return document.getElementsByClassName(\"TimeLinePageContainer\")[0].scrollHeight");
                //페이지 내리고
                js.executeScript("document.getElementsByClassName(\"TimeLinePageContainer\")[0]" +
                        ".scrollTo(0,document.getElementsByClassName(\"TimeLinePageContainer\")[0].scrollHeight)");

//                System.out.println(pageHeight);
                Thread.sleep(1000);
                driver.findElement(By.tagName("body")).sendKeys(Keys.END);

                long current_pageHeght = (long) js.executeScript
                        ("return document.getElementsByClassName(\"TimeLinePageContainer\")[0].scrollHeight");
                ;
//                System.out.println("현재페이지길이: "+current_pageHeght);

                if (current_pageHeght == pageHeight) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scroll_down(int x) throws InterruptedException {
        Actions action = new Actions(driver);
        for (int i = 0; i < x; i++) {
            action.sendKeys(Keys.chord(Keys.DOWN)).perform();
        }
        Thread.sleep(500);
    }



    public static void sub_Scroll() {

        try {
            JavascriptExecutor js_1 = (JavascriptExecutor) driver;

            // Launch the application
            Thread.sleep(2000);
            //This will scroll the web page till end.
            js_1.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}