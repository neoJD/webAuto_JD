import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
//import io.appium.java_client
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NeoStudioAOS{
    private static AppiumDriver driver;
    public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static String loginEmail = "sykim@neolab.net";
    static ArrayList<WebElement> allow = new ArrayList<>();
//    private static WebDriver driver;

    public static void setUp() throws MalformedURLException {
        capabilities.setCapability("platformName", "Android"); //플랫폼 > 안드로이드
        capabilities.setCapability("app", "/Users/jd/IdeaProjects/NeoStudioAndroid/103.apk"); //실행할 어플리케이션 경로
        capabilities.setCapability("deviceName","Zflip3"); //안써도 되는데 저는 그냥 썼어요
        capabilities.setCapability("udid","R3CN70E46SP"); //adb 연결하고나서 보이는 adb 디바이스 아이디 (cmd에 adb devices)
        capabilities.setCapability("platformVersion", "12"); //디바이스 버전
        capabilities.setCapability("appWaitActivity", "com.android.permissioncontroller.permission.ui.GrantPermissionsActivity");
        //메인 액티비티로 가기 전에 실행되는 액티비티 > 저희는 권한 관련해서 시스템 팝업이 먼저 노출돼서 그 액티비티를 적어줬습니다
        capabilities.setCapability("appActivity", "kr.neolab.neonote2.app.SplashActivity");
        //이게 메인으로 들어가는 액티비티 (네오스튜디오 실행 후 cmd에 adb shell dumpsys activity > mfocusedapp 검색해서 확인)
        capabilities.setCapability("appWaitPackage", "com.google.android.permissioncontroller");
        //메인 액티비티로 가기 전에 실행되는 액티비티의 패키지 > 이것도 권한 시스템 팝업 먼저 노출돼서요
        capabilities.setCapability("appPackage", "kr.neolab.neonote2");
        //메인 액티비티의 패키지입니다 위에 메인 액티비티 확인할 때랑 똑같이 커맨드에 쳐서 확인하시면 돼요

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        //appium inspector랑 동일하게 설정해줬어요 이건 변경 안해도 된대요

        driver = new AndroidDriver(url, capabilities);

        driver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void GoogleLogin() throws Exception{
        try {
            allowPermission();
//            driver.manage().timeouts()
//                    .implicitlyWait(20, TimeUnit.SECONDS);
            driver.findElement(MobileBy.xpath("//android.widget.TextView[contains(@resource-id, 'tos_text')]")).click();
            driver.manage().timeouts()
                    .implicitlyWait(20, TimeUnit.SECONDS);
            driver.findElement(MobileBy.xpath("//android.view.ViewGroup[@index='4']")).click();
            driver.manage().timeouts()
                    .implicitlyWait(20, TimeUnit.SECONDS);
            driver.findElement(MobileBy.xpath("//android.widget.Button[@index='1']")).click();
            driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='" + loginEmail + "']")).click();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void allowPermission() throws Exception{
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        while(driver.findElements(MobileBy.xpath("//android.widget.Button[@index='0']")).size()>0)
        {
            WebElement a = driver.findElement(MobileBy.xpath("//android.widget.Button[@index='0']"));
            a.click();
            if(driver.findElements(MobileBy.xpath("//android.widget.Button[@index='0']")).size()==0){
                break;
            }
        }
    }

    public static void CreateNotebook() throws Exception{
        WebElement details = driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                "FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/" +
                "android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.ActionBar.Tab[3]/" +
                "android.widget.LinearLayout/android.widget.ImageView"));

        details.click();
    }

    public static void main(String[] args) throws Exception {
        try {
            setUp();
            GoogleLogin();
            CreateNotebook();
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}