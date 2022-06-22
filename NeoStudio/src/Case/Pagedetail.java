package Case;
import Base.MainMenu;

import Base.PageViewItem;
import Base.RunTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Base.GoogleLogin.driver;
import static Base.PageViewItem.Back_Button;
import static Base.PageViewItem.LinkPaste_Button;
import static Base.CaseList.*;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Pagedetail{

    public static String PageTotal;
    public static String PageTotalStr;
    public static WebElement Notebook;
    public static WebElement Pagedetail;
    public static WebElement PageCount;
    public static int testcount=3;


    public static void Detail() throws InterruptedException {

        MainMenu M = new MainMenu();
        M.MainMenu();
        MainMenu.Notebook.click();
        Thread.sleep(7000);

        // 첫번째 notebook  클릭
        Notebook = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div > img"));
        Notebook.click();

        Thread.sleep(7000);

        Pagedetail = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div"));
        Pagedetail.click();
        System.out.println("==== Pagedetail 진입 완료====");
        // 상세페이지 진입 완료
        //1. 하단 전체 페이지 수 확인 - 전체 페이지 수 리스트 str->int 전환
        //pagetotal(str)-> int형 변환
    }



    public static void FileMove()throws IOException {

        File test = new File("/Users/gimhansol/Downloads");
        List<String> fileNameList = new ArrayList<String>();

        File[] fileList = test.listFiles((dir, name) -> name.endsWith("png"));
        // 특정 파일 경로에서 특정 파일명 검색 하여 송출
        //RunTime.runtime =new RunTime();
        //setStartTime.setStartTime

        ArrayList<String> getFileList = new ArrayList<>();
        //여기부터 file 옮기는 부분
        for (File file : fileList) {
            getFileList.add(file.toString());//받은 파일 리스트의 원래 경로 저장
        }
        try {
            for (int i = 0; i < getFileList.size(); i++) { //파일 리스트만큼 for문 돌리고
                File file = FileUtils.getFile(getFileList.get(i)); //원래 경로
                fileNameList.add(file.toString().substring(getFileList.get(i).lastIndexOf("/")));//받은 파일 리스트에서 파일명만 저장

//                        File fileToMove = FileUtils.getFile("/Users/gimhansol/Documents/AutomationProject/0622" + fileNameList.get(i));
//                        //옮기고 싶은 경로 생성 및 파일명 합쳐서 옮길 것
//                        FileUtils.moveFile(file, fileToMove); //원래 경로에서 옮겨갈 경로로 옮기는 부분

                Path file1 = Paths.get(getFileList.get(i));
                Path newFile1 = Paths.get("/Users/gimhansol/Documents/AutomationProject/0622" + fileNameList.get(i));

                Path newFilePath= Files.move(file1, newFile1, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(newFilePath);


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
            //testcount=3
            for (int i=1; i<=testcount;){ // 위에꺼로 바꿔주기
                PageViewItem P = new PageViewItem();
                P.Getpageviewitem();
                Thread.sleep(5000);
                //System.out.println(i);
                PageViewItem.ImageDown_Button.click();
                //PageViewItem.Next_Button.click();
                i++;
                for(int j=1; j<testcount-1;) { //코드 손좀 보기
                    Thread.sleep(2000);
                    PageViewItem.Next_Button.click();
                    j++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void Directory(){
        try{
            File dir =new File("/Users/gimhansol/Documents/AutomationProject/0622");
            //디렉토리 생성
            boolean DirectoryCreated= dir.mkdir();
            //결과 출력
            System.out.println(DirectoryCreated);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void vs(){
        File test=new File("/Users/gimhansol/Downloads");
        File newtest =new File("/Users/gimhansol/Documents/AutomationProject/0622");


        if(test==newtest) {
            item.add("기존 파일 이동 파일 일치");
            result.add("PASS");
            System.out.println("끝났다");
        }
        else{
                item.add("기존 파일 이동 파일 불일치");
                result.add("FALSE");
        }


    }
    public static void linkPaste(){
        try {
            PageViewItem P = new PageViewItem();
            P.Getpageviewitem();
            LinkPaste_Button.click();
            Thread.sleep(10000);

            WebElement linkPaste_alert = driver.findElement(By.className("MuiAlert-message"));
            if(Objects.equals(linkPaste_alert.getText(),"링크가 복사되었습니다."))
            {
                item.add("링크 복사 토스트 팝업 확인");
                result.add("PASS");
            }
            else{
//                    System.out.println("링크 복사 토스트 팝업 확인 : FAIL");
                item.add("링크 복사 토스트 팝업 확인");
                result.add("FAIL");
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
        }

    }





}

