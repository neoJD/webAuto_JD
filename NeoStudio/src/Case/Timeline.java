package Base.NeoStudio.src.Case;
import Base.NeoStudio.src.Base.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import static Base.NeoStudio.src.Base.MainMenu.*;
import static Base.GoogleLogin.driver;
import Base.NeoStudio.src.Base.Scroll;

import static Base.NeoStudio.src.Base.CaseList.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;


public class Timeline{
    public static String Total_Pages = "";
    public static int Total_Grid;
//    public static String item;
//    public static String result;

    public static void loadAll_Pages() {
        // timeline에 있는 모든 페이지 정보를 불러오는 메소드
        try {
            MainMenu M = new MainMenu();
            M.MainMenu();
            MainMenu.Timeline.click();

            Scroll.TimeLine_Scrolldown(Timeline);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void checkPages() {
        // Timeline 전체 페이지가 실제페이지와 일치하는지 확인하는 메소드
        try {
            driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
            loadAll_Pages();

            WebElement totalPages = driver.findElement(By.xpath
                    ("//*[@id=\"root\"]/div/div[1]/main/div/div/div[2]/div/div[1]/p/span"));

            // 전체 페이지
            Total_Pages = totalPages.getText(); // ex. "196 pages" 로 담겨서
            String[] Page = Total_Pages.split(" "); // Split 메소드로 숫자만 담기
            Total_Pages = Page[0]; // String

            // 페이지 생성날짜를 담은 DateList
//            ArrayList<WebElement> DateList = new ArrayList<WebElement>();
//            DateList = (ArrayList<WebElement>) driver.findElements(By.cssSelector
//                    ("div.MuiGrid-root.MuiGrid-container.MuiGrid-spacing-xs-2"));


            ArrayList<WebElement> GridList = new ArrayList<WebElement>();
            GridList = (ArrayList<WebElement>) driver.findElements(By.cssSelector
                    ("#root > div > div.defaultLayout > main > div > div > " +
                            "div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12." +
                            "MuiGrid-grid-sm-true > div > div > div > ul > div"));
            // 이미지(실제페이지) 수
            Total_Grid = GridList.size(); // int

            // 테스트 코드
            if (Total_Grid == Integer.parseInt(Total_Pages)) {
                System.out.println("전체 페이지 수 : " + Total_Pages);
                System.out.println("실제 페이지 수 : " + Total_Grid);
                System.out.println("타임라인 전체 페이지 수 확인 : PASS");
                item.add("타임라인 전체 페이지 수 확인");
                result.add("PASS");
            } else {
                System.out.println("전체 페이지 수 : " + Total_Pages);
                System.out.println("실제 페이지 수: " + Total_Grid);
                System.out.println("타임라인 전체 페이지 수 확인 : FAIL");
                item.add("타임라인 전체 페이지 수 확인");
                result.add("FAIL");
            }
            System.out.println("------------------------------------------------------------페이지 수 체크 완료------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //driver.close();
        }
    }

    public static void PagesByDate() {
        // 생성 날짜별로 생성한 페이지를 확인하는 메소드
        ArrayList<WebElement> Grid_item; // 날짜가 담긴 웹엘리먼트타입의 리스트
        ArrayList<String> Date_Month = new ArrayList<>(); //일 월
        ArrayList<String> Year = new ArrayList<>(); // 연도
        ArrayList<String> MergedDate = new ArrayList<>(); // 일 월 연

        try {
            //loadAll_Pages();
            Grid_item = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div.TimeLinePageContainer div.MuiGrid-root.MuiGrid-item h6"));
            for (int i = 0; i < Grid_item.size(); i++){
                if (i % 2 ==0) { // i가 짝수면 date_month에 담고
                    Date_Month.add(Grid_item.get(i).getText());

                } else { // 홀수면 year에 담아
                    Year.add(Grid_item.get(i).getText());
                }
            }

            for (int i=0; i<Date_Month.size(); i++){
                String s = Date_Month.get(i) + " " + Year.get(i);
                MergedDate.add(s);
            }

//            System.out.println(MergedDate); // test code


            ArrayList<Integer> Gridnumber = new ArrayList<Integer>();
            ArrayList<WebElement> Temp = new ArrayList<>();
//            int j = 2;
//            Temp = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#root > div > div.defaultLayout > main > div > div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div > div:nth-child("+Integer.toString(j)+") > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > ul > div > button"));
//            System.out.println((Temp.size()));

            for (int j=0; j<MergedDate.size(); j++) {

                Temp = (ArrayList<WebElement>) driver.findElements(By.cssSelector("#root > div > div.defaultLayout > main > div > div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div > div:nth-child(" + Integer.toString(2+j) + ") > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > ul > div > button"));
                Gridnumber.add(Temp.size());
                // Temp 초기화
                Temp.clear();
            }
//            System.out.println(Gridnumber.size());


            for (int i=0; i < MergedDate.size(); i++){
                String date = MergedDate.get(i);
                int page_number = Gridnumber.get(i);
                System.out.println("생성 날짜 : " + date + " / 페이지 수: " + page_number);
            }
            item.add("타임라인 생성 날짜 / 페이지 수 확인");
            result.add("PASS");
            System.out.println("*******************************************************Case 1. Timeline 체크 완료*******************************************************");


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //driver.close();
        }
    }

}
