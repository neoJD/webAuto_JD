package Base.NeoStudio.src.Case;
import Base.NeoStudio.src.Base.MainMenu;

import Base.NeoStudio.src.Base.PageViewItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Base.GoogleLogin.driver;
import static Base.NeoStudio.src.Base.PageViewItem.Back_Button;
import static Base.NeoStudio.src.Base.PageViewItem.LinkPaste_Button;
import static Base.NeoStudio.src.Base.CaseList.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class Pagedetail {

    public static String PageTotal;
    public static String PageTotalStr;
    public static WebElement Notebook;
    public static WebElement Pagedetail;
    public static ArrayList<WebElement> NotebookArr;
    public static WebElement PageCount;
    public static int testcount = 3;

    public static ArrayList<String> NotebookArr_sort;


    public static File file; // download 하고 손대지 않음.
    public static File newfile; // download -> dir 이동된

    public static Path newFilePath;
    public static List<String> fileNameList; //png 파일명만 추출 ,oldPathFileName_FilterList에서
    public static List<String> newFileNameList; //png 파일명만 추출
    public static List<String> getFileListPath; //oldPathFileList to path
    public static List<String> getNewFileListPath; // move path file list
    public static ArrayList<String> NotebookLIs_t; //notebook name test
    public static ArrayList<WebElement> NotebookLis; // 노트북 리스트 이름
    //public abstract String getText;
    public static int NotebookCint; //notebook count int 형
    public static WebElement NotebookN; //notebook N개
    public static String NotebookC; //notebook int count
    //public static String[] NotebookCt;
    public static String NotebookC_total;


    public static void PageDetail() throws InterruptedException {

        MainMenu M = new MainMenu();
        M.MainMenu();
        MainMenu.Notebook.click();
        Thread.sleep(7000);


        NotebookArr = new ArrayList<WebElement>();

        //NotebookN 노트북 개수 체크
        NotebookN = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/main/div/div/div/div[1]/div/div[1]/p"));
        System.out.println(NotebookN);
        System.out.println("**********test [1]  노트북 개수 체크 **********");

        NotebookC = NotebookN.getText(); //web>string

        //System.out.println(NotebookC); //전체 9 권
        String[] NotebookCt = NotebookC.split("");
        NotebookC_total= NotebookCt[3];
        NotebookCint = Integer.parseInt(NotebookC_total.substring(0));
        //노트북 개수 숫자만 받아 int형 전환
        //System.out.println(NotebookCint); //9
        System.out.println("**********test [2]  NotebookCt 노트북 개수 확인  **********");

        Thread.sleep(5000);


        for (int i = 0; i < NotebookCint; i++) {
            NotebookArr = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div.MuiBox-root> ul > div:nth-child(" + ( i + 1 ) + ")> button > div"));
            Thread.sleep(5000);
            //css가 아니라 클래스명으로 찾아야할듯 뺑뺑이 돌리기 싫다
            //System.out.println(NotebookArr); // css selector: div.MuiBox-root> ul > div:nth-child(1) > button > div]]
            System.out.println(NotebookArr);

            if (NotebookArr.isEmpty()) {
                System.out.println("NotebookArr is empty");
                NotebookArr.removeAll(Collections.singleton(null));
            }

            NotebookArr_sort = new ArrayList<String>();
            NotebookArr_sort.add(NotebookArr.toString());
            // 아래 왜 되었는지 확인하기

            //NotebookArr_sort.add(NotebookArr.getText());

            //java에서 list null check! NullPointException 관련 Err시 null값 Arr에서 제거
            if (NotebookArr_sort.isEmpty()) {
                System.out.println("NotebookArr_sort is empty");
                NotebookArr_sort.removeAll(Collections.singleton(null));
            }

            //System.out.println(NotebookArr_sort);


            Thread.sleep(7000);
            //노트북 페이지 첫페이지 선택
            Pagedetail = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div"));
            Pagedetail.click();
            System.out.println("****************** Pagedetail 진입 완료 ******************");
            //1. 파일 다운로드 2. url 3.옆으로 넘기기/ 페이지 값 체크 4. 파일 이동 5. compare 6. back key 넣어주기

        }
    }

    public static void FileMove(){
         try {
            file = new File("/Users/gimhansol/Downloads");


            fileNameList = new ArrayList<String>(); //old path file list up
            File[] fileList = file.listFiles((dir, name) -> name.endsWith("png"));
            // 특정 파일 경로에서 png 파일명 검색 하여 송출 -> fileList

            //ArrayList<String> getFileList = new ArrayList<>();
            getFileListPath = new ArrayList<>();
            //여기부터 file 옮길 배열 부분

            //File dir = new File("/Users/gimhansol/Documents/AutomationProject/0701");

            for (int i = 0; i < NotebookCint; i++) {
                File dir = new File("/Users/gimhansol/Documents/AutomationProject/07"+(i+1)+"");
                boolean DirectoryCreated = dir.mkdirs();
                //디렉토리 생성
            }

            for (File file2 : fileList) {
                getFileListPath.add(file2.toString());//받은 파일 리스트의 원래 경로 저장 -> 정보나 값들을 문자열로 만들어 리턴함.
            }

                for (int i = 0; i < getFileListPath.size(); i++) {
                    File file = FileUtils.getFile(getFileListPath.get(i)); //원래 경로
                    fileNameList.add(file.toString().substring(getFileListPath.get(i).lastIndexOf("/")));//받은 파일 리스트에서 파일명만 저장

                    Path file1 = Paths.get(getFileListPath.get(i));
                    Path newFile1 = Paths.get("/Users/gimhansol/Documents/AutomationProject/0701" + fileNameList.get(i));
                    //'이동된 경로+ fileNameList(i)' 가 덧붙여짐
                    // getFileListPath 자체가 '기존 경로 + 파일 리스트' 배열
                    System.out.println("파일네임리스트 확인 차 ");
                    System.out.println(fileNameList);

                    Path newFilePath = Files.move(file1, newFile1, StandardCopyOption.REPLACE_EXISTING);
                    //Files.move(fromPath, toPath);
                    //디렉토리에 이동 및 기존 대상 덮어쓰기
                }


         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static void imagedown( ) {
            try {
                PageCount = driver.findElement(By.cssSelector("div > span:nth-child(4)"));

                //    public static String PageTotalStr;
                PageTotalStr = PageCount.getText();

                int PageTotalInt;
                PageTotalInt = Integer.parseInt(PageTotalStr);

                //저장 누르고 -> 페이지 이동 누르고 -> PageTotalStr만큼
                for (int i = 1; i <= PageTotalInt; i++ ) {
                //testcount = 3;
                //for (int i = 1; i <= testcount; i++) { // 위에꺼로 바꿔주기
                    Thread.sleep(5000);
                    PageViewItem P = new PageViewItem();
                    P.Getpageviewitem();
                    PageViewItem.ImageDown_Button.click();


                    try {
                        LinkPaste_Button.click();
                        Thread.sleep(4000);

                        WebElement linkPaste_alert = driver.findElement(By.className("MuiAlert-message"));
                        if (Objects.equals(linkPaste_alert.getText(), "링크가 복사되었습니다.")) {
                            item.add("링크 복사 토스트 팝업 확인");
                            result.add("PASS");
                        } else {
                            item.add("링크 복사 토스트 팝업 확인");
                            result.add("FAIL");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }

                    for (int j = 1; j < PageTotalInt - 1; j++) {
                        Thread.sleep(7000);
                        PageViewItem.Next_Button.click();
                    }
                }
                Thread.sleep(3000);
                Back_Button.click();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }


    public static void Compare( ) {

            newfile = new File("/Users/gimhansol/Documents/AutomationProject/0701"); //새로 이동된 폴더
            File[] newFileList = newfile.listFiles((dir, name) -> name.endsWith("png"));

            getNewFileListPath = new ArrayList<>();
            newFileNameList = new ArrayList<>();


            for (File newfile : newFileList) {
                getNewFileListPath.add(newfile.toString());
                //System.out.println(getNewFileListPath);
            }

            try {
                for (int i = 0; i < getNewFileListPath.size(); i++) {
                    File newfile = FileUtils.getFile(getNewFileListPath.get(i));
                    System.out.println("=======newfile 지남======");
                    //System.out.println(newfile);
                    newFileNameList.add(newfile.toString().substring(getNewFileListPath.get(i).lastIndexOf("/")));
                    System.out.println("newFileNameList 확인차");
                    System.out.println(newFileNameList);

                    if (newFileNameList.isEmpty()) {
                        System.out.println("newFileNameList is empty");
                        newFileNameList.removeAll(Collections.singleton(null));

                    }
                    //java에서 list null check! NullPointException 관련 Err시 null값 Arr에서 제거

                }


                if (Objects.equals(fileNameList, newFileNameList)) {
                    item.add("기존 파일 이동 파일 일치");
                    result.add("PASS");
                    System.out.println("성공");

                } else {
                    item.add("기존 파일 이동 파일 불일치");
                    result.add("FALSE");
                    System.out.println("실패");
                }

                } catch (Exception e) {
                    e.printStackTrace();
                }

    }

}
