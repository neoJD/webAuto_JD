import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;

public class AppiumTestcase {


    private static AppiumDriver driver;

    public static void Setup() {

//Set the Desired Capabilities

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("devicePlatform", "Android");
        caps.setCapability("app", "/Users/jd/IdeaProjects/NeoStudioAndroid/103.apk"); //실행할 어플리케이션 경로
        caps.setCapability("deviceName", "Galaxy Note 20");
        caps.setCapability("udid", "R3CN70E46SP"); //Give Device ID  caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("appWaitActivity", "com.android.systemui.subscreen.SubHomeActivity");
        caps.setCapability("appWaitPackage", "com.android.systemui");
        caps.setCapability("appPackage", "kr.neolab.neonote2");
        caps.setCapability("appActivity", "kr.neolab.neonote2.app.SplashActivity");

//Instantiate Appium Driver

        try {

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        } catch (MalformedURLException e) {

            System.out.println(e.getMessage());

        }

    }


    public static void main(String[] args) {
        Setup();
    }

}


