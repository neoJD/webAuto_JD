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
    public static int fv_totalInt;

    public static void Favorite() {
        GoogleLogin G = new GoogleLogin();
        G.Login();

        MainMenu M = new MainMenu();
        M.N_MainMenu();
        MainMenu.Favorite.click();
        //--side navigation _scroll& click--
        Scroll S = new Scroll();
        S.N_Scroll();

        //0. 전체 페이지수 : 공백으로 문자열 나누기 - 엘리먼트로 그 값 가져오기
        //1. 즐겨찾기 str 배열로 만들고 리스트 하나 만들어서 전체 배열 담기

        WebElement favorite_ele = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div/div[1]/p"));
        favorite_str = favorite_ele.getText(); // web > string
        //System.out.println(favorite_str); //전체 13페이지 출력

        // 2. 공백을 의미하는 (" ")을 구분자로 문자열 스플릿
        String[] page = favorite_str.split(" ");
        fv_total = page[0]; //string 전체13를 [0]에 넣는다
        //문자열 2번째부터 출력 > 13
        //substring 문자열 자르기

        // str -> int형 변경 /int 13
        // 지저분해서 int형 fv_totalInt에 넣어줌
        //System.out.println("북마크 총 페이지:"+ fv_total.substring(2,fv_total.length()));
        //System.out.println(Integer.parseInt(fv_total.substring(2,fv_total.length())));
        fv_totalInt = Integer.parseInt(( fv_total.substring(2, fv_total.length()) )); //

        // 배열 엘리먼트 ~str 생성
        ArrayList<WebElement> favoriteAll_str = new ArrayList<WebElement>();
        //북마크 페이지 모두_string으로 받음
        favoriteAll_str = (ArrayList<WebElement>) driver.findElements(By.cssSelector("span.MuiTypography-root.MuiTypography-caption.MuiTypography-colorTextSecondary.MuiTypography-alignLeft"));

        favoriteAll_str.size();
        //System.out.println("전체 북마크 페이지 수:" + pageSize); //배열크기 ~> int형  13
        System.out.println("전체 북마크 페이지 수:" + favoriteAll_str.size()); //배열의 사이즈 13

        //if( Integer.parseInt(fv_total.substring(2,fv_total.length())) == favoriteAll_str.size()){
        if (fv_totalInt == favoriteAll_str.size()) {
            System.out.println("전체 페이지 수 일치 확인 : PASS");
        } else {
            System.out.println("전체 페이지 수 일치 확인: FAIL");
        }

    }

    public void Favorite_Run() {
        Favorite();
    }
}

