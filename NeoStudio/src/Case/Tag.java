package Base.NeoStudio.src.Case;

import Base.GoogleLogin;
import Base.NeoStudio.src.Base.MainMenu;
import Base.NeoStudio.src.Base.Scroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static Base.NeoStudio.src.Base.CaseList.item;
import static Base.NeoStudio.src.Base.CaseList.result;
import static Base.GoogleLogin.driver;


public class Tag {
    public static String Tag_total;
    public static String Tag_str;
    public static int Tag_totalInt;


    public static void checkTotalTag() throws InterruptedException {
        try {
//        GoogleLogin G = new GoogleLogin();
//        G.Login();

            MainMenu M = new MainMenu();
            M.MainMenu();
            MainMenu.Tag.click();
            //"--side navigation _scroll& click--"
            //Scroll S = new Scroll();
            //S.N_Scroll();

            // 전체 몇 페이지 수
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            WebElement Tag_ele = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/p"));
            Tag_str = Tag_ele.getText(); // web > string
            //System.out.println(Tag_str); //전체 9개 출력

            // 2. 공백을 의미하는 (" ")을 구분자로 문자열 스플릿
            // '전체/9/개' 로 되어있음
            String[] page = Tag_str.split(" ");

            for (int i = 0; i < page.length; i++) {
                // System.out.println(page[i]);
            }

            //page[0]=전체, page[1]=9, page[2]=개 , '전체12/개' '전체/9/개' 3자리면 '전체111개'??
            Tag_total = page[1];

            //string-> int형 변환 -> int형 9
            Tag_totalInt = Integer.parseInt(Tag_total);
            //System.out.println(Tag_totalInt); //9

            //배열에 넣기
            ArrayList<WebElement> TagAll_str = new ArrayList<WebElement>();
            TagAll_str = (ArrayList<WebElement>) driver.findElements(By.cssSelector("h6.MuiTypography-root.MuiTypography-subtitle1.MuiTypography-colorTextPrimary.MuiTypography-alignLeft"));

            TagAll_str.size();
            System.out.println("전체 태그 수 : " + TagAll_str.size()); //배열의 사이즈 9

            //int형 9와 배열사이즈 int 9비교
            if (Tag_totalInt == TagAll_str.size()) {
                System.out.println("전체 태그 수 일치 확인 : PASS");
                item.add("전체 태그 수 일치 확인");
                result.add("PASS");
            } else {
                System.out.println("전체 태그 수 일치 확인 : FAIL");
                item.add("전체 태그 수 일치 확인");
                result.add("FAIL");

            }
            System.out.println("*********************************************************Case 3. Tag 체크 완료*********************************************************");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
//            driver.close();
        }
    }
}