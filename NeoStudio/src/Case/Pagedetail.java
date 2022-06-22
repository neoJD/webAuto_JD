package Case;
import Base.MainMenu;

import Base.PageViewItem;
import Base.RunTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Base.GoogleLogin.driver;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pagedetail{

    public static String PageTotal;
    public static String PageTotalStr;
    public static WebElement Notebook;
    public static WebElement Pagedetail;
    public static WebElement PageCount;
    //public static int testcount=3;


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
        //pagetotal(str)-> int형 변환
    }


    //public class Move{
            public static void FileMove()throws IOException {

                File test = new File("/Users/gimhansol/Downloads");
                List<String> fileNameList = new ArrayList<String>();

                File[] fileList = test.listFiles((dir, name) -> name.endsWith("png"));
                // 특정 파일 경로에서 특정 파일명 검색 하여 송출
                //RunTime.runtime =new RunTime();
                //setStartTime.setStartTime




                ArrayList<String> getFileList = new ArrayList<>();
                //file 이동
                for (File file : fileList) {
                    getFileList.add(file.toString());//받은 파일 리스트의 원래 경로 저장


                }
                try {
                    for (int i = 0; i < getFileList.size(); i++) {
                        File file = FileUtils.getFile(getFileList.get(i));
                        fileNameList.add(file.toString().substring(getFileList.get(i).lastIndexOf("/")));//받은 파일 리스트에서 파일명만 저장
                        File fileToMove = FileUtils.getFile("/Users/gimhansol/Documents/AutomationProject/abckkkk" + fileNameList.get(i));
                        //옮기고 싶은 경로 생성 및 파일명 합쳐서 옮길 것
                        FileUtils.moveFile(file, fileToMove); //원래 경로에서 옮겨갈 경로로 옮기는 부분

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(getFileList);
            }
         //}



    public static void imagedown() {
        try {
            PageCount = driver.findElement(By.cssSelector("div > span:nth-child(4)"));
            PageTotalStr = PageCount.getText();

            int PageTotalInt;
            PageTotalInt = Integer.parseInt(PageTotalStr);

            //저장 누르고 -> 페이지 이동 누르고 -> PageTotalStr만큼
            for (int i = 1; i <= PageTotalInt; ) {
            //testcount=3
            //for (int i=1; i<=testcount;){ // 위에꺼로 바꿔주기
                PageViewItem P = new PageViewItem();
                P.Getpageviewitem();
                Thread.sleep(5000);
                //System.out.println(i);
                PageViewItem.ImageDown_Button.click();
                //PageViewItem.Next_Button.click();
                i++;
                for(int j=1; j<PageTotalInt-1;) { //코드 손좀 보기
                    Thread.sleep(2000);
                    PageViewItem.Next_Button.click();
                    j++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}

