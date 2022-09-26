package Base.NeoStudio.src.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Base.GoogleLogin.*;

// 상세 페이지 내 메인메뉴들(프린트, 이미지다운, url 다운 등의 기능들을 엘리먼트로 저장

public class PageViewItem {

    public static WebElement Print_Button;
    public static WebElement ImagePaste_Button;
    public static WebElement LinkPaste_Button;
    public static WebElement ImageDown_Button;
    public static WebElement SeeList_Button;

    public static WebElement Back_Button;
    public static WebElement Previous_Button;
    public static WebElement Next_Button;

    public static WebElement ZoomOut_Button;
    public static WebElement ZoomIn_Button;

    public static WebElement Portrait_Button;

    public static WebElement Landscape_Button;

    public static void Getpageviewitem() {

        ArrayList<WebElement> MainContainer = new ArrayList<WebElement>();
        MainContainer = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-3 > button"));
        System.out.println(MainContainer.size());
        Print_Button = MainContainer.get(0);
        ImagePaste_Button = MainContainer.get(1);
        LinkPaste_Button = MainContainer.get(2);
        ImageDown_Button = MainContainer.get(3);
        SeeList_Button = MainContainer.get(4);

        Back_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/main/div/div/div[2]/div/div[1]/button"));
        Previous_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[4]/button[1]"));
        Next_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[4]/button[2]"));
        ZoomOut_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[4]/button[3]"));
        ZoomIn_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[4]/button[4]"));
        Portrait_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[4]/button[5]"));
        Landscape_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[4]/button[6]"));

    }

}
