package Case;
import Base.MainMenu;

import Base.PageViewItem;
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

public class Pagedetail{

    public static String PageTotal;
    public static String PageTotalStr;
    public static WebElement Notebook;
    public static WebElement Pagedetail;
    public static WebElement PageCount;
    public static int testcount=3;


    public static void PageDetail() throws InterruptedException {

        MainMenu M = new MainMenu();
        M.MainMenu();
        MainMenu.Notebook.click();
        Thread.sleep(7000);

        Notebook = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div > img"));
        Notebook.click();

        Thread.sleep(7000);
        //노트북 페이지 첫페이지 선택
        Pagedetail = driver.findElement(By.cssSelector("ul > div:nth-child(1) > button > div"));
        Pagedetail.click();
        System.out.println("****************** Pagedetail 진입 완료 ******************");

    }

    public static void FileMove()throws IOException {

        File test = new File("/Users/gimhansol/Downloads");
        List<String> fileNameList = new ArrayList<String>();

        File[] fileList = test.listFiles((dir, name) -> name.endsWith("png"));
        // 특정 파일 경로에서 특정 파일명 검색 하여 송출

        ArrayList<String> getFileList = new ArrayList<>();
        //여기부터 file 옮기는 부분


        File dir =new File("/Users/gimhansol/Documents/AutomationProject/0623");
        //디렉토리 생성
        boolean DirectoryCreated = dir.mkdir();



        for (File file : fileList) {
            getFileList.add(file.toString());//받은 파일 리스트의 원래 경로 저장
        }
        try {
            for (int i = 0; i < getFileList.size(); i++) { //파일 리스트만큼 for문 돌리고
                File file = FileUtils.getFile(getFileList.get(i)); //원래 경로
                fileNameList.add(file.toString().substring(getFileList.get(i).lastIndexOf("/")));//받은 파일 리스트에서 파일명만 저장


                Path file1 = Paths.get(getFileList.get(i));
                Path newFile1 = Paths.get("/Users/gimhansol/Documents/AutomationProject/0623" + fileNameList.get(i));

                Path newFilePath= Files.move(file1, newFile1, StandardCopyOption.REPLACE_EXISTING);

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
            for (int i=1; i<=testcount; i++){ // 위에꺼로 바꿔주기
                Thread.sleep(5000);
                PageViewItem P = new PageViewItem();
                P.Getpageviewitem();
                PageViewItem.ImageDown_Button.click();


                try {
                    LinkPaste_Button.click();
                    Thread.sleep(4000);

                    WebElement linkPaste_alert = driver.findElement(By.className("MuiAlert-message"));
                    if(Objects.equals(linkPaste_alert.getText(),"링크가 복사되었습니다."))
                    {
                        item.add("링크 복사 토스트 팝업 확인");
                        result.add("PASS");
                    }
                    else{
                        item.add("링크 복사 토스트 팝업 확인");
                        result.add("FAIL");
                    }
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                } finally {
                }

                for(int j=1; j<testcount-1; j++) {
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


    public static void Compare(){
        File Test=new File("/Users/gimhansol/Downloads");
        File Newtest =new File("/Users/gimhansol/Documents/AutomationProject/0623");


        if(Test==Newtest) {
            item.add("기존 파일 이동 파일 일치");
            result.add("PASS");
        }
        else{
            item.add("기존 파일 이동 파일 불일치");
            result.add("FALSE");
        }


    }


}
