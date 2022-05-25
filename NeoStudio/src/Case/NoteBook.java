package Case;

import java.util.*;
import Base.MainMenu;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import static Base.GoogleLogin.*;
import static Base.Scroll.*;

public class NoteBook {
    ArrayList<WebElement> noteLis = new ArrayList<WebElement>();

    public void NBSorting() throws InterruptedException {
        Actions action = new Actions(driver);
        MainMenu M = new MainMenu();
        M.MainMenu();
        MainMenu.Notebook.click();

        WebElement side = driver.findElement(By.cssSelector("button > span.MuiIconButton-label > svg"));
        side.click();

        WebElement NBview = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div"));
        action.moveToElement(NBview).perform();

        ArrayList<String> noteList_side = new ArrayList<>();
        ArrayList<String> noteList = new ArrayList<>();
        ArrayList<String> noteList_sort = new ArrayList<>();

        WebElement nbook_ele = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div/div[1]/div/div[1]/p"));
        String[] nbook_str = nbook_ele.getText().split(" ");
        int note_check = Integer.parseInt(nbook_str[1]);
        //노트북 전체 갯수 (상단 전체 갯수)

        Thread.sleep(2000);

        noteLis = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div > div.MuiBox-root > ul > div"));

        int note_count = 0;

        for (int i = 0; i < noteLis.size(); i++) {
            WebElement noteNameLis_p = driver.findElement(By.cssSelector("ul > div:nth-child(" + (i + 1) + ") > p"));
            noteList.add(noteNameLis_p.getText());
            note_count++;
        }
        ArrayList<String> noteList_Nsort = (ArrayList<String>) noteList.clone();
        Collections.sort(noteList);

        WebElement nbook_comboBox = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div/div[1]/div/div[2]/button"));
        nbook_comboBox.click();

        WebElement nbook_sortName = driver.findElement(By.xpath("//*[@id=\"menu-list-grow\"]/li[2]"));
        nbook_sortName.click();

        Thread.sleep(2000);

        for (int i = 0; i < noteLis.size(); i++) {
            WebElement noteNameLis_n = driver.findElement(By.cssSelector("ul > div:nth-child(" + (i + 1) + ") > p"));
            noteList_sort.add(noteNameLis_n.getText());
            WebElement noteNameLis_side = driver.findElement(By.cssSelector("div > div.defaultLayout > div.MuiDrawer-root.MuiDrawer-docked > div > div > div > div > div:nth-child(" + (i + 1) + ") > div > div.MuiBox-root > p"));
            noteList_side.add(noteNameLis_side.getText());
        }
        System.out.println("노트 개수 : " + note_check);
        System.out.println("실제 노트 개수 : " + note_count);

        if (note_count == note_check) {
            System.out.println("노트 수 체크 : PASS");
        } else {
            System.out.println("노트 수 체크 : FAIL");
        }
        System.out.println("-----------------------------------노트 수 체크 완료---------------------------------------");

        System.out.println("정렬 전 노트 리스트 : " + Arrays.deepToString(noteList_Nsort.toArray()));
        System.out.println("사이드 네비 기대결과 : " + Arrays.deepToString(noteList_side.toArray()));
        System.out.println("기대결과 : " + Arrays.deepToString(noteList.toArray()));
        System.out.println("실제결과 : " + Arrays.deepToString(noteList_sort.toArray()));

        if (Arrays.equals(noteList.toArray(), noteList_sort.toArray()) && Arrays.equals(noteList_side.toArray(), noteList_sort.toArray()) == true) {
            System.out.println("노트 이름순 정렬 : PASS");
        } else {
            System.out.println("노트 이름순 정렬 : FAIL");
        }
        System.out.println("---------------------------------------노트 정렬 완료--------------------------------------------");
    }

    public void pageCheck(){
        try {
            Thread.sleep(1000);

            ArrayList<WebElement> page = new ArrayList<>();
            page = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div.MuiBox-root > ul > div"));

            Thread.sleep(2000);
            System.out.println("카운트된 필기 페이지 수 : " + page.size());
            if (page.size() == 0) {
                System.out.println("*데이터 깨진 상태의 노트북*");
            } else {
            }

            WebElement pageWrt_L = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[1]/button[2]/h5"));
            WebElement page_R = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[2]/div[1]/div/div[1]/p"));
            System.out.println("네비게이션 바 내 필기 페이지 수 : " + pageWrt_L.getText());

            Thread.sleep(500);
            String page_str_write = page_R.getText().substring(22);
            String[] splited1 = page_str_write.split(" ");
            String page_write = (splited1[0] + splited1[1]).trim();

            System.out.println("메인페이지 내 필기 페이지 수 : " + page_write);

            if (Objects.equals(pageWrt_L.getText(), page_write) && Objects.equals(pageWrt_L.getText(), Integer.toString(page.size()))) {
                System.out.println("필기 페이지 수 일치 : PASS");
            } else {
                System.out.println("필기 페이지 수 일치 : FAIL");
            }

            ArrayList<WebElement> pageLis = new ArrayList<WebElement>();
            pageLis = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div.MuiBox-root > ul > div"));

            ArrayList<String> pageName = new ArrayList<>();
            ArrayList<String> pageName_sort = new ArrayList<>();

            for (int i = 0; i < pageLis.size(); i++) {
                WebElement pageList = driver.findElement(By.cssSelector("ul > div:nth-child(" + (i + 1) + ") > span.MuiTypography-root.MuiTypography-caption.MuiTypography-colorTextPrimary.MuiTypography-alignLeft"));
                pageName.add(pageList.getText());
                Thread.sleep(1000);
            }
            System.out.println("이름순 정렬 전 페이지 리스트 : " + pageName);
            Collections.sort(pageName);
            System.out.println("실제 이름순 정렬된 페이지 리스트 : " + pageName);

            WebElement pageComboButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[2]/div[1]/div/div[2]/button"));
            pageComboButton.click();

            WebElement pageNameSorting = driver.findElement(By.xpath("//*[@id=\"menu-list-grow\"]/li[2]"));
            pageNameSorting.click();
            Thread.sleep(1000);

            for (int i = 0; i < pageLis.size(); i++) {
                WebElement pageList = driver.findElement(By.cssSelector("ul > div:nth-child(" + (i + 1) + ") > span.MuiTypography-root.MuiTypography-caption.MuiTypography-colorTextPrimary.MuiTypography-alignLeft"));
                pageName_sort.add(pageList.getText());
            }
            System.out.println("이름순 정렬된 페이지 리스트 : " + pageName_sort);

            if (Arrays.equals(pageName.toArray(), pageName_sort.toArray()) == true) {
                System.out.println("페이지 이름순 정렬 : PASS");
            } else {
                System.out.println("페이지 이름순 정렬 : FAIL");
            }

            WebElement pageAll = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[1]/button[1]"));
            pageAll.click();

            ArrayList<WebElement> page2 = new ArrayList<>();
            page2 = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div.MuiBox-root > ul > div"));

            Thread.sleep(2000);
            System.out.println("카운트된 전체 페이지 수 : " + page2.size());

            WebElement pageAll_L = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[1]/button[1]/h5"));
            System.out.println("네비게이션 바 내 전체 페이지 수 : " + pageAll_L.getText());

            String page_str_all = page_R.getText();
            String[] splited2 = page_str_all.split("/");
            String page_all = splited2[1];
            System.out.println("메인페이지 내 전체 페이지 수 : " + page_all);

            if (Objects.equals(pageAll_L.getText(), Integer.toString(page2.size())) && Objects.equals(pageAll_L.getText(), page_all)) {
                System.out.println("전체 페이지 수 일치 : PASS");
            } else {
                System.out.println("전체 페이지 수 일치 : FAIL");
            }
        } catch (Exception e) {
        }
    }

    public void NBpageList() throws InterruptedException {
        ArrayList<WebElement> noteList_side = new ArrayList<>();

        for (int i = 0; i < noteLis.size(); i++) {
            WebElement noteNameLis_side = driver.findElement(By.cssSelector("div > div.defaultLayout > div.MuiDrawer-root.MuiDrawer-docked> div > div > div > div > div:nth-child(" + (i + 1) + ") > div > div.MuiBox-root > p"));
            noteList_side.add(noteNameLis_side);
            noteList_side.get(i).click();
            System.out.println("[" + noteList_side.get(i).getText() + "]");
            pageCheck();
            System.out.println("---------------------------------------" + (i + 1) + "번째 노트북 -------------------------------------------");
            noteList_side.get(i).click();
            scroll_down(4);
        }
    }

    public void NoteBook_Run() throws InterruptedException {
        NBSorting();
        NBpageList();
    }
}
