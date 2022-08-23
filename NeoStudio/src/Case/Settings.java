package Base.NeoStudio.src.Case;

import Base.NeoStudio.src.Base.GoogleLogin;
import Base.NeoStudio.src.Base.Listeners;
import Base.NeoStudio.src.Base.MainMenu;
import Base.NeoStudio.src.Base.Scroll;
import com.beust.ah.A;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static Base.NeoStudio.src.Base.CaseList.item;
import static Base.NeoStudio.src.Base.CaseList.result;
import static Base.NeoStudio.src.Base.GoogleLogin.driver;
import static Base.NeoStudio.src.Base.MainMenu.Setting;
import static Base.NeoStudio.src.Base.Scroll.scroll_down;

public class Settings {

    public static void LogOut() {

        // 로그아웃 메소드
        MainMenu M = new MainMenu();
        M.MainMenu();
        Setting.click();

        WebElement logout_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/ul/li[1]/div[2]/button"));
        logout_Button.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement confirm_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div/div/ul/button[2]/span[1]"));
//        confirm_Button.click(); // 확인 버튼 클릭 (로그아웃 할 경우)

        WebElement cancel_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div[3]/div/div/ul/button[1]/span[1]"));
        cancel_Button.click(); // 취소 버튼 클릭 (로그아웃 하지 않을 경우)
    }


    public static void ChangeTheme() {

        try {
            MainMenu M = new MainMenu();
            M.MainMenu();
            Setting.click();
            Thread.sleep(2000);

            WebElement change_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/ul/li[2]/div[2]/div/button\n"));

            //테마 "html/body" 컬러 확인을 위한 엘리먼트
            WebElement body = driver.findElement(By.xpath("/html/body"));

        /*
        #121212 : 블랙 // 일반(기본) 모드일때
        #f5f5f5 : 회색 // 다크 모드일때
         */
            String blackTheme = "#ffffff";
            String whiteTheme = "#121212";
            String current_rgbaColor = body.getCssValue("color"); // rgba 컬러
            String current_hexColor = Color.fromString(current_rgbaColor).asHex(); // rgba 컬러 포맷을 hex로 변환

            // 결과 확인
            if (current_hexColor.equals(blackTheme)) {
                // 블랙테마일경우
                change_Button.click();
                Thread.sleep(3000);

                // 바뀌었는지 확인
                current_rgbaColor = body.getCssValue("color"); // rgba 컬러
                current_hexColor = Color.fromString(current_rgbaColor).asHex();

                if (current_hexColor.equals(whiteTheme)) {
                    System.out.println("현재 hexColor: " + current_hexColor + ", 블랙테마에서 화이트테마로 변경 : PASS");
                    item.add("블랙테마에서 화이트테마로 변경");
                    result.add("PASS");
                } else {
                    System.out.println("테마 변경에 실패하였습니다.");
                    item.add("블랙테마에서 화이트테마로 변경");
                    result.add("Fail");
                }


            } else {
                // 화이트 테마일경우
                change_Button.click();
                Thread.sleep(3000);

                // 바뀌었는지 확인
                current_rgbaColor = body.getCssValue("color"); // rgba 컬러
                current_hexColor = Color.fromString(current_rgbaColor).asHex();

                if (current_hexColor.equals(blackTheme)) {
                    System.out.println("현재 hexColor: " + current_hexColor + ", 화이트테마에서 블랙테마로 변경: PASS");
                    item.add("화이트테마에서 블랙테마로 변경");
                    result.add("PASS");
                } else {
                    System.out.println("테마 변경에 실패하였습니다.");
                    item.add("화이트테마에서 블랙테마로 변경");
                    result.add("Fail");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void Check_Ligal_Policy() {
        //언어별로 개인정보, 이용약관 페이지 열고 확인

        EventFiringWebDriver eventHandler = new EventFiringWebDriver(driver);
        Listeners listener = new Listeners();
        eventHandler.register(listener);

        try {
            MainMenu M = new MainMenu();
            M.MainMenu();
            Setting.click();


            WebElement Privacy_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/ul/li[4]/div[2]/button"));
            WebElement Terms_Condition_Button = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/ul/li[4]/div[3]/button"));

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //원래 탭 originalTap 설정
//            String originalTap = driver.getWindowHandle();


            // 언어선택 버튼 엘리먼트 생성
            WebElement language_button = driver.findElement(By.xpath
                    ("//*[@id=\"root\"]/div/div/main/div/div/ul/li[5]/div[2]/button"));


            //언어 4개 languages 에 저장 [영어,한국어,일본어,독일어]
            ArrayList<WebElement> languages = new ArrayList<>();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


            for (int i = 0; i < 4; i++) {

                language_button.click();
                Thread.sleep(3000);

                // 언어 선택 버튼이 눌릴때마다 각 언어에 할당된 엘리먼트 값들이 변경(혹은 초기화 되서) 이렇게 매번 버튼 클릭 -> 값 받기를 진행해야함
                WebElement ENG = driver.findElement(By.xpath("//*[@id=\"menu-list-grow\"]/li[1]"));
                WebElement KOR = driver.findElement(By.xpath("//*[@id=\"menu-list-grow\"]/li[2]"));
                WebElement JPN = driver.findElement(By.xpath("//*[@id=\"menu-list-grow\"]/li[3]"));
                WebElement DUT = driver.findElement(By.xpath("//*[@id=\"menu-list-grow\"]/li[4]"));

                languages.add(0, ENG);
                languages.add(1, KOR);
                languages.add(2, JPN);
                languages.add(3, DUT);

                languages.get(i).click(); // 언어 선택
                Thread.sleep(2000);
                Privacy_Button.click(); // 개인정보 선택
                Thread.sleep(3000);

                List<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1)); //새탭으로 포커싱
                Thread.sleep(1000);

                WebElement privacyPage = eventHandler.findElement(By.xpath("/html"));
                String privacyPage_check = privacyPage.getText(); //개인정보 텍스트 받음 > 이때 이벤트 리스너
                driver.close(); //개인정보 tab 닫음

                driver.switchTo().window(tabs.get(0)); // 원래 탭으로 이동
                Thread.sleep(3000);

                Terms_Condition_Button.click(); // 약관 선택
                Thread.sleep(3000);
                List<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs2.get(1)); //새탭으로 포커싱

                WebElement termsPage = eventHandler.findElement(By.xpath("/html"));
                String termsPage_check = termsPage.getText(); //약관 텍스트 받음 > 이때 이벤트 리스너
                driver.close(); //약관 tab 닫음

                driver.switchTo().window(tabs2.get(0)); // 원래 탭으로 이동
                Thread.sleep(3000);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            driver.close();
        }
        eventHandler.unregister(listener);
    }
}
