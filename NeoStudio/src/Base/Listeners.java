package Base.NeoStudio.src.Base;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import java.io.File;
import java.io.IOException;

import static Base.NeoStudio.src.Base.CaseList.*;

public class Listeners implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver driver) {


    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("/Users/sykim/AutomationProject/ExceptionCapture/ExceptionScreenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //예외 상황일 경우 지정된 경로에 스크린샷 저장하도록 함
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) { //getText 동작이 트리거로 동작하는 리스너
        String url = driver.getCurrentUrl();
        String language = url.substring(url.length() - 3, url.length());

        if (element.getText().contains("Page Not Found") == true) { //페이지 에러 뜨면요

            String url_replace = driver.getCurrentUrl().substring(8).replace("/", "_"); //url 내에 / 포함된 상태 > 파일 경로로 인식되기 때문에 _로 치환
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("/Users/sykim/AutomationProject/ExceptionCapture/" + url_replace + ".png"));
            }
            //노출된 페이지 내에 에러 문구 있을 경우 해당 페이지의 스크린샷을 지정된 경로에 저장되도록 함/ !!!!!!여기는 사용하실 때 경로 한 번씩 바꿔주세요!!!!!
            catch (IOException e) {
                e.printStackTrace();
            }

            //url에서 언어 정보만 가져옴
            switch (language) {
                case "ko/":
                    if (url.contains("policy")) {
                        item.add("한국어 개인정보처리방침 페이지 확인");
                        result.add("FAIL");
                    } else {
                        item.add("한국어 이용약관 페이지 확인");
                        result.add("FAIL");
                    }
                    break;
                case "en/":
                    if (url.contains("policy")) {
                        item.add("영어 개인정보처리방침 페이지 확인");
                        result.add("FAIL");
                    } else {
                        item.add("영어 이용약관 페이지 확인");
                        result.add("FAIL");
                    }
                    break;
                case "/ja":
                    if (url.contains("policy")) {
                        item.add("일본어 개인정보처리방침 페이지 확인");
                        result.add("FAIL");
                    } else{
                        item.add("일본어 이용약관 페이지 확인");
                        result.add("FAIL");
                    }
                    break;
                case "/de":
                    if (url.contains("policy")) {
                        item.add("독일어 개인정보처리방침 페이지 확인");
                        result.add("FAIL");
                    } else {
                        item.add("독일어 이용약관 페이지 확인");
                        result.add("FAIL");
                    }
                    break;
            }//스위치문으로 체크해서 엑셀로 파싱할 리스트로 옮겨줌
        } else { //에러페이지가 아니면요
            switch (language) {
                case "ko/":
                    if (url.contains("policy")) {
                        item.add("한국어 개인정보처리방침 페이지 확인");
                        result.add("PASS");
                    } else {
                        item.add("한국어 이용약관 페이지 확인");
                        result.add("PASS");
                    }
                    break;
                case "en/":
                    if (url.contains("policy")) {
                        item.add("영어 개인정보처리방침 페이지 확인");
                        result.add("PASS");
                    } else {
                        item.add("영어 이용약관 페이지 확인");
                        result.add("PASS");
                    }
                    break;
                case "/ja":
                    if (url.contains("policy")) {
                        item.add("일본어 개인정보처리방침 페이지 확인");
                        result.add("PASS");
                    } else {
                        item.add("일본어 이용약관 페이지 확인");
                        result.add("PASS");
                    }
                    break;
                case "/de":
                    if (url.contains("policy")) {
                        item.add("독일어 개인정보처리방침 페이지 확인");
                        result.add("PASS");
                    } else {
                        item.add("독일어 이용약관 페이지 확인");
                        result.add("PASS");
                    }
                    break;
            }
        }
    }

}
