package Base.NeoStudio.src.Base;

import Base.NeoStudio.src.Case.NoteBook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import static Base.GoogleLogin.driver;

public class MainMenu {
    public static WebElement Timeline;
    public static WebElement Notebook;
    public static WebElement Tag;
    public static WebElement Record;
    public static WebElement Favorite;
    public static WebElement Setting;


    public static void MainMenu() {

        try {
            Thread.sleep(2000);

            WebElement categoryTime = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[2]"));
            Actions bulider = new Actions(driver);
            bulider.click(categoryTime)
                .sendKeys(Keys.chord(Keys.PAGE_DOWN))
                .perform();

            // Timeline 클리 후 페이지다운키 누르기

            // mainMenuName 어레이 리스트에 각 메뉴의 엘리먼트 할당하기
            WebElement temp;
            ArrayList<WebElement> mainMenuName = new ArrayList<WebElement>(); //메인메뉴 webelement 담는 배열
            for(int i = 1; i<7; i++){
                temp = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div/div[2]/li["+i+"]"));
                mainMenuName.add(temp);
            }

            // Test code
//            System.out.println(mainMenuName.size());

            Timeline = mainMenuName.get(0);
            Notebook= mainMenuName.get(1);
            Tag= mainMenuName.get(2);
            Record= mainMenuName.get(3);
            Favorite= mainMenuName.get(4);
            Setting= mainMenuName.get(5);
//
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

}
