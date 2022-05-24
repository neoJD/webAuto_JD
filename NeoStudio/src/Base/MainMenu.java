package Base;

import org.openqa.selenium.By;
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


    public void N_MainMenu() {
      
        try {

            Thread.sleep(2000);

            WebElement categoryTime = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[1]/span"));
            WebElement scrolldown = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[6]/span"));
            // setting xpath 위치로 스크롤 다운 실행
            Actions builder = new Actions(driver);

            builder.clickAndHold(categoryTime).moveToElement(scrolldown).perform();
            // click 하고 홀드된 상태에서 셋팅 위치까지 스크롤 다운 


            ArrayList<WebElement> mainMenuName =new ArrayList<WebElement>(); //메인메뉴 webelement 담는 배열

            mainMenuName = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li.MuiButtonBase-root.MuiListItem-root.MuiMenuItem-root.jss43.MuiMenuItem-gutters.MuiListItem-gutters.MuiListItem-button"));

            Timeline = mainMenuName.get(0);
            Notebook= mainMenuName.get(1);
            Tag= mainMenuName.get(2);
            Record= mainMenuName.get(3);
            Favorite= mainMenuName.get(4);
            Setting= mainMenuName.get(5);

        }  catch (Exception e) {
            e.printStackTrace();
        }

    }



}
        





        




