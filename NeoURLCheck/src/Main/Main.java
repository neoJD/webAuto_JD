package Base.NeoURLCheck.src.Main;

import Base.NeoURLCheck.src.Base.*;

import static Base.NeoURLCheck.src.Base.CheckURL.*;
import static Base.NeoURLCheck.src.Base.UrlList.*;

public class Main {
    public static void main(String[] args) {
        try {
            CheckURL.urlCheck_run();
            if (error_urlList.size() > 0) {
                HtmlJavaSend.sendEmail();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}
