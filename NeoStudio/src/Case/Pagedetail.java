package Case;
import Base.MainMenu;

import Base.PageViewItem;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Base.GoogleLogin.driver;
import static Base.PageViewItem.Back_Button;
import static Base.PageViewItem.LinkPaste_Button;
import static Base.CaseList.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Consumer;

public class Pagedetail {

    public static String PageTotal;
    public static String PageTotalStr;
    public static WebElement Notebook;
    public static WebElement Pagedetail;
    public static WebElement PageCount;
    public static int testcount = 3;

    public static List<String> Notebook_sort;


    public static File file; // download 하고 손대지 않음.
    public static File newfile; // download -> dir 이동된

    public static Path newFilePath;
    public static List<String> fileNameList; //png 파일명만 추출 ,oldPathFileName_FilterList에서
    public static List<String> newFileNameList; //png 파일명만 추출
    public static List<String> getFileListPath; //oldPathFileList to path
    public static List<String> getNewFileListPath; // move path file list



    public static void PageDetail() throws InterruptedException {

        MainMenu M = new MainMenu();
        M.MainMenu();
        MainMenu.Notebook.click();
        Thread.sleep(7000);

        //0623. 노트북 돌아가면서 클릭 for(int i=0; i<css~~~; i++){
        ArrayList<> NotebookArr = new ArrayList<>();

        for(int i=0; i<NotebookArr.size(); i++){
        //Notebook = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div > img"));
         Notebook=driver.findElement(By.cssSelector("ul > div:nth-child("+(i)+ ") > button > div > img”));
         Notebook_sort.add(Notebook.getText());
         Notebook.click();
         Thread.sleep(7000);
         //노트북 페이지 첫페이지 선택
         Pagedetail = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div"));
         Pagedetail.click();
         System.out.println("****************** Pagedetail 진입 완료+ (i)+ ******************");


        }


//        Thread.sleep(7000);
//        //노트북 페이지 첫페이지 선택
//        Pagedetail = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div"));
//        Pagedetail.click();
//        System.out.println("****************** Pagedetail 진입 완료+ (i)+ ******************");

    }

    public static void FileMove() throws IOException {

        file = new File("/Users/gimhansol/Downloads");


        fileNameList = new ArrayList<String>(); //old path file list up
        File[] fileList = file.listFiles((dir, name) -> name.endsWith("png"));
        // 특정 파일 경로에서 png 파일명 검색 하여 송출 -> fileList

        //ArrayList<String> getFileList = new ArrayList<>();
        getFileListPath = new ArrayList<>();
        //여기부터 file 옮길 배열 부분

        File dir = new File("/Users/gimhansol/Documents/AutomationProject/0701");
        boolean DirectoryCreated = dir.mkdirs();
        //디렉토리 생성


        for (File file2 : fileList) {
            getFileListPath.add(file2.toString());//받은 파일 리스트의 원래 경로 저장 -> 정보나 값들을 문자열로 만들어 리턴함.
        }
        try {
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

    public static void imagedown() {
        try {
            PageCount = driver.findElement(By.cssSelector("div > span:nth-child(4)"));
            PageTotalStr = PageCount.getText();

            int PageTotalInt;
            PageTotalInt = Integer.parseInt(PageTotalStr);

            //저장 누르고 -> 페이지 이동 누르고 -> PageTotalStr만큼
            //for (int i = 1; i <= PageTotalInt; ) {
            //testcount = 3;
            for (int i = 1; i <= testcount; i++) { // 위에꺼로 바꿔주기
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

                for (int j = 1; j < testcount - 1; j++) {
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


    public static void Compare() {

        newfile = new File("/Users/gimhansol/Documents/AutomationProject/0701"); //새로 이동된 폴더
        File[] newFileList = newfile.listFiles((dir, name) -> name.endsWith("png"));

        getNewFileListPath = new ArrayList<>();
        newFileNameList= new ArrayList<>();


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

                if(newFileNameList.isEmpty()){
                    System.out.println("newFileNameList is empty");
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
        } finally {
        }

    }
}
