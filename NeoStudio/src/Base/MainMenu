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
            // click 하고 홀드된 상태에서 셋팅위치로 내리는
            //System.out.println("=============test2: main menu 스크롤 성공========");


            ArrayList<String> mainMenuName = new ArrayList<>(); //메인메뉴 좌표 담는 배열
            WebElement mainMenu = null;
            for (int n = 1; n <7; n++) {
                mainMenu = (WebElement) driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[" + n + "]"));
                mainMenuName.add(mainMenu.getText()); //mainMenu에서 텍스트 가져와서 xy에 저장 타임라인- 노트북
            }
            //System.out.println(mainMenu); //메인 메뉴 names 출력됨

            Timeline = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[1]"));
            Notebook = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[2]"));
            Tag = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[3]"));
            Record = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[4]"));
            Favorite= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[5]"));
            Setting = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div[2]/li[6]"));

        }  catch (Exception e) {
            e.printStackTrace();
        }

    }



}
        




