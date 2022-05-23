package Base;

import org.openqa.selenium.JavascriptExecutor;
import static Base.GoogleLogin.driver;

public class Scroll{

    public static void N_Scroll() {
      
        try {

            //driver.get("https://neostudio-staging.web.app/main/bookmark");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Launch the application

            Thread.sleep(2000);
            //This will scroll the web page till end.
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
