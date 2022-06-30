package Main;

import Base.*;
import static Base.CheckURL.*;

public class Main {
    public static void main(String[] args) {
        try {
            CheckURL.urlCheck_run();
            HtmlJavaSend.sendEmail();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.close();
        }
    }
}
