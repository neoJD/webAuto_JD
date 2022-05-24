package Case;

import Base.GoogleLogin;
import Base.MainMenu;
import Base.Scroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static Base.GoogleLogin.driver;

public class Favorite {
    public static String fv_total;
    public static String favorite_str;


    public static void main(String[] args) {
        GoogleLogin G = new GoogleLogin();
        G.Login();

        MainMenu M = new MainMenu();
        M.N_MainMenu();
        MainMenu.Favorite.click();

        Scroll S = new Scroll();
        S.N_Scroll();

        WebElement favorite_ele = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div/div[1]/p"));
        favorite_str = favorite_ele.getText(); 

        // 2. 공백을 의미하는 (" ")을 구분자로 문자열 스플릿
        String[] page = favorite_str.split(" ");
        fv_total = page[0]; //string 전체13 [0]에 넣는다
        //substring 문자열 자르기, 문자열 2번째부터 출력 > 13
        System.out.println("북마크 총 페이지:"+ fv_total.substring(2,fv_total.length()));


    
        ArrayList<WebElement> favoriteAll_str =new ArrayList<WebElement>();
        favoriteAll_str = (ArrayList<WebElement>) driver.findElements(By.cssSelector("span.MuiTypography-root.MuiTypography-caption.MuiTypography-colorTextSecondary.MuiTypography-alignLeft"));

        int pageSize = favoriteAll_str.size();
        System.out.println("전체 북마크 페이지 수:" + pageSize); //배열크기 ~> int형  13
        System.out.println("전체 북마크 페이지 수:" + favoriteAll_str.size()); //배열의 사이즈 13

        if( pageSize == favoriteAll_str.size()){
            System.out.println("전체 페이지 수 일치 확인 : PASS");
        } else {
            System.out.println("젠체 페이지 수 일치 확인: FAIL");
        }
