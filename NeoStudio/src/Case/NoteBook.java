package Base.NeoStudio.src.Case;

import java.util.*;

import Base.NeoStudio.src.Base.MainMenu;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static Base.NeoStudio.src.Base.CaseList.item;
import static Base.NeoStudio.src.Base.CaseList.result;
import static Base.GoogleLogin.*;
import static Base.NeoStudio.src.Base.Scroll.*;


public class NoteBook {

    public static boolean wrtPageCount;
    public static boolean allPageCount;
    public static boolean pageListSorting;
    public static boolean emptyData;
    public static ArrayList<String> noteNumber = new ArrayList<>();

    static ArrayList<WebElement> noteLis = new ArrayList<WebElement>();

    public static void checkSorting() throws InterruptedException {
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
            item.add("노트북 개수 일치 확인");
            result.add("PASS");
        } else {
            System.out.println("노트 수 체크 : FAIL");
            item.add("노트북 개수 일치 확인");
            result.add("FAIL");
        }
        System.out.println("-------------------------------------------------------------노트 수 체크 완료-------------------------------------------------------------");

        System.out.println("정렬 전 노트 리스트 : " + Arrays.deepToString(noteList_Nsort.toArray()));
        System.out.println("사이드 네비 기대결과 : " + Arrays.deepToString(noteList_side.toArray()));
        System.out.println("기대결과 : " + Arrays.deepToString(noteList.toArray()));
        System.out.println("실제결과 : " + Arrays.deepToString(noteList_sort.toArray()));

        if (Arrays.equals(noteList.toArray(), noteList_sort.toArray()) && Arrays.equals(noteList_side.toArray(), noteList_sort.toArray()) == true) {
            System.out.println("노트 이름순 정렬 : PASS");
            item.add("노트 이름순 정렬 확인");
            result.add("PASS");
        } else {
            System.out.println("노트 이름순 정렬 : FAIL");
            item.add("노트 이름순 정렬 확인");
            result.add("FAIL");
        }
        System.out.println("--------------------------------------------------------------노트 정렬 완료--------------------------------------------------------------");
    }

    public static void checkPageList() {
        try {
            Thread.sleep(1000);

            ArrayList<WebElement> page = new ArrayList<>();
            page = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div.MuiBox-root > ul > div"));

            Thread.sleep(2000);
            System.out.println("카운트된 필기 페이지 수 : " + page.size());
            if (page.size() == 0) {
                System.out.println("*데이터 깨진 상태의 노트북*");
                emptyData = true;
            } else {
            }

            WebElement pageWrt_L = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[1]/button[2]/h5"));
            WebElement page_R = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/main/div/div/div[2]/div[1]/div/div[1]/p"));
            System.out.println("네비게이션 바 내 필기 페이지 수 : " + pageWrt_L.getText());

            Thread.sleep(500);

            String t = page_R.getText();
            String[] page_Wsplit = t.split("⏐");
            String page_arr = page_Wsplit[1];
            String[] page_write = page_arr.substring(10).split("/");

            System.out.println("메인페이지 내 필기 페이지 수 : " + page_write[0].trim());

            if (Objects.equals(pageWrt_L.getText(), page_write[0].trim()) && Objects.equals(pageWrt_L.getText(), Integer.toString(page.size()))) {
                System.out.println("필기 페이지 수 일치 : PASS");
                wrtPageCount = true;
            } else {
                System.out.println("필기 페이지 수 일치 : FAIL");
                wrtPageCount = false;
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
                pageListSorting = true;
            } else {
                System.out.println("페이지 이름순 정렬 : FAIL");
                pageListSorting = false;
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
                allPageCount = true;
            } else {
                System.out.println("전체 페이지 수 일치 : FAIL");
                allPageCount = false;
            }
        } catch (Exception e) {
        }
    }

    public static void checkNotebookPage() throws InterruptedException {
        ArrayList<WebElement> noteList_side = new ArrayList<>();

        for (int i = 0; i < noteLis.size(); i++) {
            WebElement noteNameLis_side = driver.findElement(By.cssSelector("div > div.defaultLayout > div.MuiDrawer-root.MuiDrawer-docked> div > div > div > div > div:nth-child(" + (i + 1) + ") > div > div.MuiBox-root > p"));
            noteList_side.add(noteNameLis_side);
            noteList_side.get(i).click();
            System.out.println("[" + noteList_side.get(i).getText() + "]");
            checkPageList();
            System.out.println("--------------------------------------------------------------" + (i + 1) + "번째 노트북-------------------------------------------------------------");
            noteList_side.get(i).click();
            scroll_down(4);
            item.add((i + 1) + "번째 노트북 페이지 확인");
            if (allPageCount && wrtPageCount && pageListSorting == true) {
                result.add("PASS");
            } else if (emptyData == true) {
                result.add("FAIL");
            } else {
                result.add("FAIL");
            }
        }
        System.out.println("*******************************************************Case 2. Notebook 체크 완료*******************************************************");
    }
}