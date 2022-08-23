package Base.NeoStudio.src.Case;

import Base.NeoStudio.src.Base.GoogleLogin;
import Base.NeoStudio.src.Base.MainMenu;
import Base.NeoStudio.src.Base.Scroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Base.NeoStudio.src.Base.CaseList.item;
import static Base.NeoStudio.src.Base.CaseList.result;
import static Base.GoogleLogin.driver;
import static Base.NeoStudio.src.Base.Scroll.scroll_down;

public class Record {


    public static String Rc_total;
    public static String Rc_str;
    public static int Rc_totalInt;


    public static void checkTotalRecord() throws InterruptedException {
        MainMenu M = new MainMenu();
        M.MainMenu();
        scroll_down(3);
        MainMenu.Record.click();

        //==scroll & click ==
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement Rc_ele = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/p"));
        //System.out.println(Rc_ele); //전체/115/개
        Rc_str = Rc_ele.getText(); // web > string
        String[] page = Rc_str.split(" ");

        for (int i = 0; i < page.length; i++) {
            //System.out.println(page[i]); // page[0]= 전체 ,page[1] =115, page[2]=개
        }
        Rc_total = page[1];
        Rc_totalInt = Integer.parseInt(Rc_total);

        ArrayList<WebElement> RcAll_str = new ArrayList<WebElement>();
        RcAll_str = (ArrayList<WebElement>) driver.findElements(By.cssSelector("h6.MuiTypography-root.MuiTypography-subtitle1.MuiTypography-colorTextPrimary.MuiTypography-alignLeft"));

        RcAll_str.size();
        System.out.println("전체 녹음 개수 : " + RcAll_str.size()); //배열의 사이즈 115

        //int형 9와 배열사이즈 int 9비교
        if (Rc_totalInt == RcAll_str.size()) {
            System.out.println("전체 녹음 수 일치 확인 : PASS");
            item.add("전체 녹음 수 일치 확인");
            result.add("PASS");
        } else {
            System.out.println("전체 녹음 수 일치 확인 : FAIL");
            item.add("전체 녹음 수 일치 확인");
            result.add("FAIL");
        }
        System.out.println("********************************************************Case 4. Record 체크 완료********************************************************");
    }

}
