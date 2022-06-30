package Main;

import Base.*;

import static Base.CheckURL.*;
import static Base.UrlList.*;

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
