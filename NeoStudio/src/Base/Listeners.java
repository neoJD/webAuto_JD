package Base;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.remote.http.WebSocket;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;
import java.io.IOException;

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
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
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
        if(element.getText().contains("Page Not Found")==true){
            System.out.println("에러 페이지 노출 : " + driver.getCurrentUrl());
            String url = driver.getCurrentUrl().substring(8).replace("/", "_"); //url 내에 / 포함된 상태 > 파일 경로로 인식되기 때문에 _로 치환
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File("/Users/sykim/AutomationProject/ExceptionCapture/" + url + ".png"));
            } 
            //노출된 페이지 내에 에러 문구 있을 경우 해당 페이지의 스크린샷을 지정된 경로에 저장되도록 함
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
        }
    }

}
